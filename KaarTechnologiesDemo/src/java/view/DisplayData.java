
package view;

import dao.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DisplayData extends HttpServlet {
 String query,name;
    Connection conn;
    Statement stmt;
    ResultSet res;
    DBConnection dbconn;
    List lst=new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
            
            dbconn=new DBConnection();
            conn=dbconn.setConnection();
         
            query="select * from manu";
            res=dbconn.getResult(query, conn);
            while(res.next()){
               lst.add(res.getString("num"));
               lst.add(res.getString("fromdate"));
               lst.add(res.getString("todate"));
               lst.add(res.getString("des"));
               lst.add(res.getString("role"));
               lst.add(res.getString("tech"));   
            }
             
            res.close();
        }catch (Exception e){
         
        }finally{
            request.setAttribute("eData", lst);
            RequestDispatcher rd=request.getRequestDispatcher("/displaydata.jsp");
            
            rd.forward(request, response);
            lst.clear();
            out.close();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
