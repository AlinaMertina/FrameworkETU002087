package etu002087.framework.servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.String;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Vector;
import etu002087.framework.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;

public class FrontServlet extends HttpServlet{
    String baseurl;
    String nompakage;
    HashMap<String,Mapping> MappingUrls ;

    public void init() throws ServletException {
        nompakage=this.getInitParameter("racine_D");
        baseurl=this.getInitParameter("base_url_site");//definition de la base url de l'aplication dans le web.xml
        //alimentation de l'attribut MappingUrls
        MappingUrls= new HashMap<String,Mapping>();
       //class respensable de prendre recurcivement le class dans le dossier
        Vector<String> classdefin = new Vector<>();
        this.getclass(nompakage,classdefin,".class");
            for(String cheminclass :classdefin){
                try{
                   
                    String string = (cheminclass.split(this.getInitParameter("split_class")))[1].replace('/','.');
                    if(string.contains("annotation")!=true){
                        String[] st = string.split(".class");
                        Class c = Class.forName(st[0]); 
                        for(Method method :c.getDeclaredMethods()){
                            if(method.isAnnotationPresent(Urlannotation.class) ){
                                Urlannotation index = method.getAnnotation(Urlannotation.class);
                                MappingUrls.put(index.index(),new Mapping(st[0], method.getName()));
                            }
                        }
                    } 
                }catch(Exception e){
                }    
            }
    }
    public Object set_atribue_class(Object o,HttpServletRequest req)throws ServletException, IOException{
            Class c = o.getClass();
            try {
                for(Method method :c.getDeclaredMethods()){
                    if(method.isAnnotationPresent(Set_value_jspannotation.class)){
                        Set_value_jspannotation annotation_method=method.getAnnotation(Set_value_jspannotation.class);
                        //verification caractere valide
                        if(method.getParameterTypes()[0]==Integer.class){
                            method.invoke(o,Integer.parseInt(req.getParameter(annotation_method.nom_atribue())));
                        }
                        else if(method.getParameterTypes()[0]==Double.class){
                            method.invoke(o,Double.parseDouble(req.getParameter(annotation_method.nom_atribue())));
                        }
                        else if(method.getParameterTypes()[0]==String.class){
                            method.invoke(o,req.getParameter(annotation_method.nom_atribue()));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return o;
    }
    public void redirecte(ModelView nomjs ,HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        HashMap<String,Object> valuer = nomjs.getItem();
        for(String key : valuer.keySet()) {
            req.setAttribute(key, valuer.get(key));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(nomjs.getnompage());
        dispatcher.forward(req, res);
    }
    public void methode_class(String indexmap,Class  c,HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        for(Method method :c.getDeclaredMethods()){
            try {
                if(method.getName().compareTo(MappingUrls.get(indexmap).getMethod())==0){
                    Object object_cl=set_atribue_class(c.getConstructor().newInstance(),req);
                    if(method.getReturnType()==ModelView.class){
                        redirecte((ModelView )method.invoke(object_cl, (Object[])null),req,res);
                    }else{
                        method.invoke(object_cl, (Object[])null);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    protected  void processRequest(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.println("togna");
        String lien =req.getRequestURL().toString();
        out.println("lien"+req.getRequestURL().toString());
        String[] controleur= lien.split(baseurl);
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        if(controleur.length>0 ){
            if(MappingUrls.get(controleur[1])!=null){
                try{
                    methode_class(controleur[1],Class.forName(MappingUrls.get(controleur[1]).getclassName()),req,res);
                }catch(Exception e){
                    out.println(e);
                }
            }

        }
        
    } 
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        processRequest(req, res);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        processRequest(req, res);
    }
    
    public void getclass(String nompackage,Vector<String>vector,String extension){
        File dossiermere=new File(nompackage);
        File[] liste = dossiermere.listFiles();
        if(liste!=null){
            for(int i=0;i<liste.length;i++){
                if(liste[i].isDirectory()==true){
                    getclass(liste[i].getPath(),vector,extension);
                }
                else if(liste[i].getName().contains(extension)==true){
                    vector.add(liste[i].getPath());
                }
        }
        }
       
    }
   
    // public HashMap<String,Object> value_get(String url){
    //     HashMap<String,Object> resulta=new HashMap<String,Object>();
    //     String[] valeur=url.split("&");
    //     for(int i=0;i<valeur.length;i++){
    //         String[] section=valeur[i].split("=");//varible qui contient le valeur 
    //         if(section.length==2){
    //             resulta.put(section[0],string_to_objet(section[1]));
    //         }else{
    //             return new HashMap<String,Object>();
    //         }
    //     }
    //     return resulta;
    // }
    public Object string_to_objet(String str){
        if(str.matches(".*[a-zA-Z].*")==true){
            return str;
        }else{
            if(str.contains(".")){
                return Double.parseDouble(str);
            }
            else if(str.contains(",")){
                str = str.replace(",", ".");
                return Double.parseDouble(str);
            }
            else{
                return Integer.parseInt(str);
            }
        }
    }
    
}
