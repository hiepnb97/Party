/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hiepn
 */
@WebServlet(name="LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Chuyển tiếp request đến trang login.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(authenticate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            response.sendRedirect("list");
        } else {
            request.setAttribute("error", "Invalid username or password!");
            
            // Chuyển tiếp request về lại trang login.jsp để hiển thị thông báo
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    /**
    * Phương thức kiểm tra thông tin đăng nhập (giả lập).
    */
    private boolean authenticate(String username, String password) {
        // Kiểm tra username và password có trùng khớp không (mô phỏng kiểm tra với DB)
        return "admin".equals(username) && "123456".equals(password);
    }
}
