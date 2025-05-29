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
 *
 * @author hiepn
 */
@WebServlet(name="ListServlet", urlPatterns={"/list"})
public class ListServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Lấy data
        PersonDAO personDAO = new PersonDAO();
        ArrayList<Person> persons;
        try {
            persons = personDAO.getAllPersons();
            request.setAttribute("persons", persons);
        
            // Chuyển tiếp request đến trang list.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
