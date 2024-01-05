package mft.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException{
    //get PrintWriter
//    PrintWriter pw = (res.getWriter());
//    //set Content type
//    res.setContentType ("text/hmtl");
//    //read the form values
//    String name = req.getParameter("name");
//    String city = req.getParameter("city");
//    String mobile = req.getParameter ("mobile");
//    String dob = req.getParameter ("dob");
//
//    System.out.println("Name: "+name);
//    System.out.println("City: "+city);
//    System.out.println("mobile: "+mobile);
//    System.out.println("dob: "+dob);
//close the stream pw. close);

}
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        // Auto-generated method stub
        doGet(req, resp);
    }
}
// TODO Problem in Database with Html
