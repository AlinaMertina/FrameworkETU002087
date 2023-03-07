package etu002087.framework.servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.String;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Vector;

public class FrontServlet extends HttpServlet{
    String baseurl=new String();

    public void init() throws ServletException {
        baseurl=this.getInitParameter("base_url_site");//definition de la base url de l'aplication dans le web.xml
    }
    protected  void processRequest(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String lien =req.getRequestURL().toString();
        String[] controleur= lien.split(baseurl);
        //controleur[1] nom du controler 
    } 
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        
        processRequest(req, res);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        processRequest(req, res);
    }
}
