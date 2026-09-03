package controller;

import dao.StudentService;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost
            (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Hiii...am successfully working ");

        String id=req.getParameter("id");
        String name=req.getParameter("Username");
        String email=req.getParameter("Email");
        String redgNo =req.getParameter("redg");
        String password=req.getParameter("password");

        Student student=new Student();

        student.setId(Integer.parseInt(id));
        student.setName(name);
        student.setEmail(email);
        student.setRedgNo(redgNo);
        student.setPassword(password);

        StudentService service=new StudentService();
        int result=service.register(student);
        if(result>0){
            resp.sendRedirect("login.html");
            System.out.println("Student register Successfully");
        }else{
            System.out.println("Student registration fail");
        }







    }
}