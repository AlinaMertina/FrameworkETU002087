package etu002087.framework.servlet;

import etu002087.framework.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.String;
import java.lang.reflect.*;
import java.util.HashMap;
public class FrontServlet extends HttpServlet{
    HashMap<String,Mapping> attribue;
   
    public void init() throws ServletException {
            attribue=new HashMap<String,Mapping>();
            attribue.put("model",new Mapping("Emp","findAll"));
    }
  
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        PrintWriter out = res.getWriter();
        String lien =req.getRequestURL().toString();
           
        Mapping classuse= attribue.get(getfirstword(lien));
        if(classuse.getclassName().compareTo("Emp")==0){
            modelEmp(classuse.getMethod(),out);
        }
    }
    public String getfirstword(String link){
        String[] value =link.split("/Sprint1");
        String[] tabevalue= value[1].split("/");
        return tabevalue[1];
    }
    public void modelEmp(String nommethod,PrintWriter out){
        Emp emp = new Emp("TOTO","Mertina Claudie");
        String valuesObject = new String();
        for(Method method : emp.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(Methodannotation.class) 
            && method.getAnnotation(Methodannotation.class).name().compareTo(nommethod)==0){
                try {
                    valuesObject = (String) method.invoke(emp);
                 } catch (Exception e) { out.print(emp+"fonction modelEmp ligne 39");}
            }
        }
        out.print(valuesObject);
    }

} 