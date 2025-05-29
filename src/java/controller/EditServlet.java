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
import model.Person;

/**
 * EditServlet xử lý việc chỉnh sửa thông tin Person.
 * Servlet này xử lý cả GET request (hiển thị form chỉnh sửa)
 * và POST request (cập nhật thông tin Person).
 * Sau khi cập nhật thành công, người dùng được chuyển hướng về trang danh sách.
 * 
 * @author hiepn
 */
@WebServlet(name="EditServlet", urlPatterns={"/edit"})
public class EditServlet extends HttpServlet {
   
    /**
     * Xử lý GET request - hiển thị form chỉnh sửa với thông tin Person hiện tại
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet
     * @throws IOException nếu có lỗi I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            // Lấy ID của Person cần chỉnh sửa từ parameter
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Khởi tạo PersonDAO và lấy thông tin Person theo ID
            PersonDAO personDAO = new PersonDAO();
            Person person = personDAO.getPersonByID(id);
            
            // Lưu thông tin Person vào request attribute
            request.setAttribute("person", person);
            
            // Chuyển tiếp đến trang edit.jsp để hiển thị form chỉnh sửa
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Log lỗi nếu có vấn đề khi truy vấn database
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Xử lý POST request - cập nhật thông tin Person vào database
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
            // Lấy thông tin Person đã được chỉnh sửa từ form
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            
            // Tạo đối tượng Person mới với thông tin đã cập nhật
            Person person = new Person(id, name, age);
            
            // Cập nhật thông tin vào database
            PersonDAO personDAO = new PersonDAO();
            personDAO.updatePerson(person);
            
            // Chuyển hướng về trang danh sách sau khi cập nhật thành công
            response.sendRedirect("list");
        } catch (SQLException ex) {
            // Log lỗi nếu có vấn đề khi cập nhật database
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
