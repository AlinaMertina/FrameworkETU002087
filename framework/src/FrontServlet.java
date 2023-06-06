package etu002087.framework.servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import java.lang.String;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Vector;
import etu002087.framework.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;

import java.util.regex.Matcher;


@MultipartConfig(
  fileSizeThreshold = 1024 * 1024,  // Taille de la mémoire tampon pour les parties
  maxFileSize = 5 * 1024 * 1024,    // Taille maximale pour un fichier individuel
  maxRequestSize = 10 * 1024 * 1024 // Taille maximale totale de la requête
)
public class FrontServlet extends HttpServlet{
    String baseurl;
    String nompakage;
    HashMap<String,Mapping> MappingUrls ;
    HashMap<String,Object> Singleton;

    public void init() throws ServletException {
        nompakage=this.getInitParameter("racine_D");
        baseurl=this.getInitParameter("base_url_site");//definition de la base url de l'aplication dans le web.xml
        //alimentation de l'attribut MappingUrls
        MappingUrls= new HashMap<String,Mapping>();
        Singleton = new HashMap<String,Object>();
       //class respensable de prendre recurcivement le class dans le dossier
        Vector<String> classdefin = new Vector<>();
        this.getclass(nompakage,classdefin,".class");//avoir tout le classe
            for(String cheminclass :classdefin){
                try{
                    String string = (cheminclass.split(this.getInitParameter("split_class")))[1].replace('/','.');
                    if(string.contains("annotation")!=true){
                        String[] st = string.split(".class");
                        Class c = Class.forName(st[0]); 
                        //si la classe est une singleton alors ajouter dans le hashmap
                        //isAnnotationPresent no nampiasaina mba amatarana oe tena io anatation io marina no amin'le class
                        if(c.isAnnotationPresent(Scopeannotation.class)){
                            Scopeannotation identification=(Scopeannotation)c.getAnnotation(Scopeannotation.class);
                            if(identification.indication().compareTo("singleton")==0){
                                Singleton.put(c.getName(),null);
                            }
                        }
                        for(Method method :c.getDeclaredMethods()){
                            if(method.isAnnotationPresent(Urlannotation.class) ){
                                Urlannotation index = method.getAnnotation(Urlannotation.class);
                                MappingUrls.put(index.index(),new Mapping(st[0], method.getName()));
                            }
                        }
                    } 
                }
                catch(Exception e){
                }    
            }
        
    }
    public Object singleton(Class c, PrintWriter out) throws Exception{
        String index=c.getName();
        if(Singleton.containsKey(index)){
           
            if(Singleton.get(index)==null){
                Object o =c.getConstructor().newInstance();
                Singleton.put(index,o);
                out.println("content "+index);
             }
            return Singleton.get(index);
        }else{
            out.println(index);
            return c.getConstructor().newInstance();
        }
        
        
    }
    public Object set_atribue_class(Object o,HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
        PrintWriter out = res.getWriter();
            Class c = o.getClass();
            try {
                for(Method method :c.getDeclaredMethods()){
                    
                    if(method.isAnnotationPresent(Set_value_jspannotation.class)){
                        Set_value_jspannotation annotation_method=method.getAnnotation(Set_value_jspannotation.class);
                        //verification caractere valide
                        if(method.getParameterTypes()[0]==Integer.class){
                            method.invoke(o,(Integer) valide_Integer(req.getParameter(annotation_method.nom_atribue())));
                        }
                        else if(method.getParameterTypes()[0]==Double.class){
                            method.invoke(o,(Double) valide_double(req.getParameter(annotation_method.nom_atribue())));
                        }
                        else if(method.getParameterTypes()[0]==String.class){
                            method.invoke(o, req.getParameter(annotation_method.nom_atribue()));
                        }
                        else if(method.getParameterTypes()[0]==Date.class){
                            Date d=(Date) string_to_objet(req.getParameter(annotation_method.nom_atribue()),out);
                            method.invoke(o,d);
                        }
                        else if(method.getParameterTypes()[0]==Float.class){
                            method.invoke(o,(Float) valide_Float(req.getParameter(annotation_method.nom_atribue())));
                        }
                        else if(method.getParameterTypes()[0]==String[].class){
                            String[] values=req.getParameterValues(annotation_method.nom_atribue()+"[]");
                            Object[] ob=new Object[1];
                            ob[0]=values;
                            method.invoke(o,ob);
                        }
                        else if(method.getParameterTypes()[0]==FileUpload.class){
                            method.invoke(o,traitement_file_uplaod(req,annotation_method.nom_atribue()));
                        }
                    }
                }
            } catch (Exception e) {
                out.println(e);
            }
            return o;
    }
    //fonction qui va traiter les fichier uploader
    public FileUpload traitement_file_uplaod(HttpServletRequest req,String nom_file)throws ServletException, IOException{
        try {
            Part filePart = req.getPart(nom_file);
            FileUpload file = new FileUpload(filePart.getSubmittedFileName(),filePart.getContentType(),filePart.getSize(),filePart.getInputStream());
            return file;
        } catch (Exception e) {
            return null;
        } 
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
        PrintWriter out = res.getWriter();
        for(Method method :c.getDeclaredMethods()){
            try {
                if(method.getName().compareTo(MappingUrls.get(indexmap).getMethod())==0){
                    Urlannotation annotation_method=method.getAnnotation(Urlannotation.class);
                    Object object_cl=set_atribue_class(singleton(c,out),req,res);
                    if(method.getReturnType()==ModelView.class){
                        
                        if(method.getParameterCount()>0){
                            ModelView m =(ModelView )method.invoke(object_cl,alimentation_parametre_fonction(method,annotation_method.nomparametre(),req,out));
                            redirecte(m ,req,res);
                        }else{
                            ModelView m = (ModelView )method.invoke(object_cl, (Object[])null);
                            redirecte( m, req,res);
                        }
                    }else{
                        if(method.getParameterCount()>0){
                            method.invoke(object_cl,alimentation_parametre_fonction(method,annotation_method.nomparametre(),req,out));
                        }else{
                            method.invoke(object_cl, (Object[])null);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public Object[] alimentation_parametre_fonction(Method fonction,String[] nomparametre,HttpServletRequest req,PrintWriter out){
        Object[] resulta = new Object[1];
            try{
                Class[] parametre = fonction.getParameterTypes();
                resulta=new Object[parametre.length];
                for(int i=0;i<parametre.length;i++){
                    if(parametre[i]==Integer.class){
                        resulta[i] =(Integer) valide_Integer(req.getParameter(nomparametre[i]));
                    }
                    else if(parametre[i]==Double.class){
                        resulta[i] = (Double) valide_double(req.getParameter(nomparametre[i]));
                    }
                    else if(parametre[i]==String.class){
                        resulta[i] = req.getParameter(nomparametre[i]);
                    }
                    else if(parametre[i]==Date.class){
                        resulta[i] =(Date) string_to_objet(req.getParameter(nomparametre[i]),out);
                    }
                    else if(parametre[i]==Float.class){
                        resulta[i]=(Float) valide_Float(req.getParameter(nomparametre[i]));
                    }
                    else if(parametre[i]==String[].class){
                        resulta[i]=req.getParameterValues(nomparametre[i]+"[]");
                    }
                    else if(parametre[i]==FileUpload.class){
                        resulta[i]=traitement_file_uplaod(req,nomparametre[i]);
                    }
                }

            } catch (Exception e) {
                out.println(e);
            }
            return resulta;
    }
    //fonction qui va regarder si l'index proposer par le client existe ou pas ou si il y a un erreur
    //cette fonction return l'index de MappingUrls correspondant
    public String get_classe_lien(String url_application){
        String[] section= url_application.split(baseurl);
        if(section.length>1){
            if (MappingUrls.get(section[1])==null ){
                return new String("not_found");
            }else{
                return section[1];
            }
        }
        else{
            return new String("index");
        }
    }

    protected  void processRequest(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
        String lien =req.getRequestURL().toString();

        // out.println("lien"+req.getRequestURL().toString());
        String controleur= get_classe_lien(lien) ;
        if(MappingUrls.get(controleur)!=null){
            try{
                methode_class(controleur,Class.forName(MappingUrls.get(controleur).getclassName()),req,res);
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
//cette fonction prendras tout les classe existant dans le repertoir de travaille du developpeur
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
   
   
    public Object string_to_objet(String str,PrintWriter out){
        Object resulta= new Object();
        if(look_if_string_is_date(str)==true && (str.contains("-")==true ||  str.contains("/")==true) ){
            
            int[] date =transfo_date_valide(str,out);
            out.println("jour"+date[2]);
            @SuppressWarnings("deprecation")
            Date d =new Date(date[0],date[1],date[2]);
            return d;
        }
        if(str.matches(".*[a-zA-Z].*")==true){
            return str;
        }
        return resulta;
    }
    public Double valide_double(String str){
        if(str==null){ return null ; }
        if (str.matches("^\\d*\\.?\\d+$")==true || str.matches("^\\d*\\,?\\d+$") ){
            if(str.contains(".")){
                return Double.parseDouble(str);
            }
            else if(str.contains(",")){
                str = str.replace(",", ".");
                return Double.parseDouble(str);
            }
        }
        if (str.matches("^\\d+$")==true){
            return Double.parseDouble(str);
        }
        return 0.0;
        
    }
    public Float valide_Float(String str){
        if(str==null){ return  null;}
            
        if (str.matches("^\\d*\\.?\\d+$")==true || str.matches("^\\d*\\,?\\d+$") ){
            if(str.contains(".")){
                return Float.parseFloat(str);
            }
            else if(str.contains(",")){
                str = str.replace(",", ".");
                return Float.parseFloat(str);
            }
        }
        if (str.matches("^\\d+$")==true){
            return Float.parseFloat(str);
        }
        return null;
        
    }
    public Integer valide_Integer(String str){
        if(str==null){ return 0;}
        if (str.matches("^\\d+$")==true){
            return  Integer.parseInt(str);
        }
        return 0;
    }
    
    public boolean look_if_string_is_date(String chaine){
        String regex = "^[0-9/-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(chaine);
        
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public int[] transfo_date_valide(String date,PrintWriter out){
        int[] resulta = new int[3];
        String[] date_split=new String[1];
        
        if(date.contains("-")==true){
            date_split=date.split("-");
        }
        if(date.contains("/")==true){
            date_split=date.split("/");
        }
        if(date_split.length>2){
            int[] resul = {Integer.parseInt(date_split[0]),Integer.parseInt(date_split[1]),Integer.parseInt(date_split[2])};
            return trie_date(resul ,0);
            
        }else{
            return resulta;
        }
    }
    public int[] trie_date(int[] date,int i){
        int max=date[i];
        int indice_max=i;
        for(int a=i;a<date.length;a++){
            if(max<date[a]){
                int value_mave=date[i];
                date[i]=date[a];
                date[a]=date[i];
            }
        }
        date[1]=date[1];
        date[2]=date[2];
        return date;
    }
}
