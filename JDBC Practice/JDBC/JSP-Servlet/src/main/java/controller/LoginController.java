package controller;

import dao.StudentService;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            Cookie cookie = new Cookie("currentUser", email);
            resp.sendRedirect("Home.html");

        } else {
            System.out.println("Login failed: Invalid email or password");
            resp.sendRedirect("login.html");
        }
    }
}
