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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

/**
 * ListServlet xử lý việc hiển thị danh sách tất cả các Person.
 * Servlet này chỉ xử lý GET request, lấy dữ liệu từ database
 * thông qua PersonDAO và hiển thị lên trang list.jsp.
 * 
 * @author hiepn
 */
@WebServlet(name="ListServlet", urlPatterns={"/list"})
public class ListServlet extends HttpServlet {
   
    /**
     * Xử lý GET request - lấy và hiển thị danh sách Person
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException nếu có lỗi servlet
     * @throws IOException nếu có lỗi I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Khởi tạo đối tượng PersonDAO để thao tác với database
        PersonDAO personDAO = new PersonDAO();
        ArrayList<Person> persons;
        try {
            // Lấy danh sách tất cả Person từ database
            persons = personDAO.getAllPersons();
            
            // Lưu danh sách vào request attribute để JSP có thể truy cập
            request.setAttribute("persons", persons);
        
            // Tạo RequestDispatcher và chuyển tiếp đến trang list.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            // Log lỗi nếu có vấn đề khi truy vấn database
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
