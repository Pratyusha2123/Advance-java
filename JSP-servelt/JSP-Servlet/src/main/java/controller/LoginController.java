package controller;

import dao.StudentService;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null) {
            resp.sendRedirect("login.html");
            return;
        }

        StudentService service = new StudentService();
        Student validStudent = service.validateStudent(email, password);
        System.out.println(validStudent);

        if (validStudent != null) {
            System.out.println("Login successfully for: " + validStudent.getName());
//            Cookie cookie=new Cookie("email", email); //utiliy class Cookie
//            cookie.setMaxAge(60*60); //set cookie for 30 minute
//            resp.addCookie(cookie);
//            resp.sendRedirect("Home.jsp");

            HttpSession session=req.getSession();
            session.setAttribute("email", email);
            session.setAttribute("name", validStudent.getName());
            session.setAttribute("regdno", validStudent.getRedgNo());
            resp.sendRedirect("Home.jsp");

        } else {
            System.out.println("Login failed: Invalid email or password");
            resp.sendRedirect("login.html");
        }
    }
}