/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.PersonDAO;
import jakarta.servlet.RequestDispatcher;
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
 * AddServlet xử lý việc thêm mới một Person vào database.
 * Servlet này xử lý cả GET request (hiển thị form thêm mới)
 * và POST request (lưu thông tin Person mới).
 * Sau khi thêm thành công, người dùng được chuyển hướng về trang danh sách.
 * 
 * @author hiepn
 */
@WebServlet(name="AddServlet", urlPatterns={"/add"})
public class AddServlet extends HttpServlet {
   
    /**
     * Xử lý GET request - hiển thị form thêm mới Person
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet
     * @throws IOException nếu có lỗi I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Tạo đối tượng RequestDispatcher để chuyển tiếp đến trang add.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");

        // Chuyển tiếp request và response đến trang add.jsp để hiển thị form
        dispatcher.forward(request, response);
    } 

    /**
     * Xử lý POST request - lưu thông tin Person mới vào database
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet
     * @throws IOException nếu có lỗi I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Lấy thông tin Person mới từ form
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        
        // Tạo đối tượng Person mới từ dữ liệu form
        Person person = new Person(name, age);
        
        // Khởi tạo PersonDAO và thêm Person mới vào database
        PersonDAO personDAO = new PersonDAO();
        try {
            personDAO.addPerson(person);
            // Chuyển hướng về trang danh sách sau khi thêm thành công
            response.sendRedirect("list");
        } catch (SQLException ex) {
            // Log lỗi nếu có vấn đề khi thêm vào database
            Logger.getLogger(AddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
