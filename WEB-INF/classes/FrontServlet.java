package etu002087.framework.servlet;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.String;
public class FrontServlet extends HttpServlet{
    /**
     * TraiteForm1
     */
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        PrintWriter out = res.getWriter();
        String lien =req.getRequestURL().toString();
        String[] value =lien.split("8080/");
        out.print("hhhhh"+value[1]);

    }
   

} 
