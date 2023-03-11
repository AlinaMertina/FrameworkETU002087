package etu002087.framework.servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.String;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Vector;
import etu002087.framework.*;;

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
    protected  void processRequest(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String lien =req.getRequestURL().toString();
        String[] controleur= lien.split(baseurl);
        if(MappingUrls.get(controleur[1])!=null){
            try{
                Class c = Class.forName(MappingUrls.get(controleur[1]).getclassName()); 
                for(Method method :c.getDeclaredMethods()){
                    if(method.getName().compareTo(MappingUrls.get(controleur[1]).getMethod())==0){
                        //invoke fonction 
                        method.invoke(c.getConstructor().newInstance(), (Object[])null);
                    }
                }
            }catch(Exception e){
                out.println(e);
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
}
