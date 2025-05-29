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
 * LoginServlet xử lý các request liên quan đến đăng nhập.
 * Servlet này xử lý cả GET request (hiển thị form đăng nhập)
 * và POST request (xử lý thông tin đăng nhập).
 * Sau khi đăng nhập thành công, người dùng được chuyển hướng đến trang danh sách.
 * 
 * @author hiepn
 */
@WebServlet(name="LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
   
    /**
     * Xử lý GET request - hiển thị form đăng nhập
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet
     * @throws IOException nếu có lỗi I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Tạo đối tượng RequestDispatcher để chuyển tiếp đến trang login.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        
        // Chuyển tiếp request và response đến trang login.jsp
        dispatcher.forward(request, response);
    } 

    /**
     * Xử lý POST request - xử lý thông tin đăng nhập
     * Kiểm tra username và password, tạo session nếu đăng nhập thành công
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet
     * @throws IOException nếu có lỗi I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Lấy thông tin đăng nhập từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Kiểm tra thông tin đăng nhập
        if(authenticate(username, password)) {
            // Nếu đăng nhập thành công, tạo session mới
            HttpSession session = request.getSession();
            // Lưu username vào session
            session.setAttribute("username", username);
            
            // Chuyển hướng người dùng đến trang danh sách
            response.sendRedirect("list");
        } else {
            // Nếu đăng nhập thất bại, đặt thông báo lỗi
            request.setAttribute("error", "Invalid username or password!");
            
            // Chuyển tiếp về lại trang login.jsp kèm thông báo lỗi
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    /**
     * Phương thức kiểm tra thông tin đăng nhập (giả lập)
     * So sánh với thông tin đăng nhập cố định (admin/123456)
     * 
     * @param username tên đăng nhập
     * @param password mật khẩu
     * @return true nếu thông tin đăng nhập hợp lệ, false nếu không
     */
    private boolean authenticate(String username, String password) {
        // So sánh username và password với giá trị cố định
        // Sử dụng equals để so sánh chuỗi an toàn, tránh null
        return "admin".equals(username) && "123456".equals(password);
    }
}
