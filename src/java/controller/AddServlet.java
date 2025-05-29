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
 *
 * @author hiepn
 */
@WebServlet(name="AddServlet", urlPatterns={"/add"})
public class AddServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Tạo một đối tượng chuyển tiếp đến một tài nguyên nội bộ (ví dụ: JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");

        // Chuyển yêu cầu hiện tại đến tài nguyên khác
        dispatcher.forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        
        Person person = new Person(name, age);
        
        PersonDAO personDAO = new PersonDAO();
        try {
            personDAO.addPerson(person);
            response.sendRedirect("list");
        } catch (SQLException ex) {
            Logger.getLogger(AddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
