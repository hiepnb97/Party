/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.PersonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DeleteServlet xử lý việc xóa một Person khỏi database.
 * Servlet này chỉ xử lý POST request để đảm bảo an toàn khi xóa dữ liệu.
 * Sau khi xóa thành công, người dùng được chuyển hướng về trang danh sách.
 * 
 * @author hiepn
 */
@WebServlet(name="DeleteServlet", urlPatterns={"/delete"})
public class DeleteServlet extends HttpServlet {
  
    /**
     * Xử lý POST request - xóa Person khỏi database theo ID
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet
     * @throws IOException nếu có lỗi I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            // Lấy ID của Person cần xóa từ parameter
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Khởi tạo PersonDAO và thực hiện xóa Person
            PersonDAO personDAO = new PersonDAO();
            personDAO.delete(id);
            
            // Chuyển hướng về trang danh sách sau khi xóa thành công
            response.sendRedirect("list");
        } catch (SQLException ex) {
            // Log lỗi nếu có vấn đề khi xóa từ database
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
