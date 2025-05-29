/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Person;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hiepn
 */
public class PersonDAO extends DBContext{
    
    public ArrayList<Person> getAllPersons() throws SQLException {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM Persons;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {            
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
            
            list.add(person);
        }
        rs.close();
        stmt.close();
        return list;
    }
    
    public Person getPersonByID(int id) throws SQLException {
        Person person = new Person();
        String sql = "SELECT * FROM Persons WHERE id = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {            
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
        }
        rs.close();
        pstmt.close();
        return person;
    }
    
    public void addPerson(Person person) throws SQLException {
        String sql = "INSERT INTO Persons (name, age) VALUES (?, ?);";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, person.getName());
        pstmt.setInt(2, person.getAge());
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    public int updatePerson(Person person) throws SQLException {
        String sql = "UPDATE Persons SET name = ?, age = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, person.getName());
        pstmt.setInt(2, person.getAge());
        pstmt.setInt(3, person.getId());
        int rows = pstmt.executeUpdate();
        pstmt.close();
        return rows;
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Persons WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
     }
    
}
