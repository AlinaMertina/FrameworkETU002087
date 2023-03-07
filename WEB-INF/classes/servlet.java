import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class servlet extends HttpServlet{
    /**
     * TraiteForm1
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req,res);
    }
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestUrl = req.getRequestURL().toString();
        String host = req.getHeader("Host");
        PrintWriter out = res.getWriter();
        requestUrl = requestUrl.split("//")[1].replace(host, "");
        out.print(requestUrl);
    }

} 
