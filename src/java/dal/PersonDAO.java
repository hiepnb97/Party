/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Person;
import java.sql.*;
import java.util.ArrayList;

/**
 * PersonDAO class cung cấp các phương thức để thao tác với bảng Persons trong database.
 * Class này kế thừa từ DBContext để sử dụng kết nối database.
 * Implement các thao tác CRUD (Create, Read, Update, Delete) cơ bản.
 * 
 * @author hiepn
 */
public class PersonDAO extends DBContext{
    
    /**
     * Lấy danh sách tất cả các Person từ database
     * @return ArrayList<Person> chứa tất cả các Person
     * @throws SQLException nếu có lỗi khi truy vấn database
     */
    public ArrayList<Person> getAllPersons() throws SQLException {
        // Khởi tạo ArrayList để lưu kết quả
        ArrayList<Person> list = new ArrayList<>();
        
        // Chuẩn bị câu lệnh SQL SELECT
        String sql = "SELECT * FROM Persons;";
        Statement stmt = connection.createStatement();
        
        // Thực thi câu lệnh và lấy kết quả
        ResultSet rs = stmt.executeQuery(sql);
        
        // Duyệt qua từng dòng kết quả
        while (rs.next()) {            
            // Tạo đối tượng Person mới và set các thuộc tính
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
            
            // Thêm Person vào danh sách
            list.add(person);
        }
        
        // Đóng các tài nguyên
        rs.close();
        stmt.close();
        return list;
    }
    
    /**
     * Lấy thông tin Person theo ID
     * @param id ID của Person cần tìm
     * @return Person object nếu tìm thấy
     * @throws SQLException nếu có lỗi khi truy vấn database
     */
    public Person getPersonByID(int id) throws SQLException {
        // Khởi tạo đối tượng Person để lưu kết quả
        Person person = new Person();
        
        // Chuẩn bị câu lệnh SQL SELECT với điều kiện WHERE
        String sql = "SELECT * FROM Persons WHERE id = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        // Set giá trị parameter
        pstmt.setInt(1, id);
        
        // Thực thi câu lệnh và lấy kết quả
        ResultSet rs = pstmt.executeQuery();
        
        // Nếu tìm thấy kết quả, set các thuộc tính cho Person
        if (rs.next()) {            
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
        }
        
        // Đóng các tài nguyên
        rs.close();
        pstmt.close();
        return person;
    }
    
    /**
     * Thêm một Person mới vào database
     * @param person Person object cần thêm
     * @throws SQLException nếu có lỗi khi thêm vào database
     */
    public void addPerson(Person person) throws SQLException {
        // Chuẩn bị câu lệnh SQL INSERT
        String sql = "INSERT INTO Persons (name, age) VALUES (?, ?);";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        // Set các giá trị parameter
        pstmt.setString(1, person.getName());
        pstmt.setInt(2, person.getAge());
        
        // Thực thi câu lệnh INSERT
        pstmt.executeUpdate();
        
        // Đóng PreparedStatement
        pstmt.close();
    }
    
    /**
     * Cập nhật thông tin của một Person
     * @param person Person object chứa thông tin cần cập nhật
     * @return số dòng bị ảnh hưởng bởi câu lệnh update
     * @throws SQLException nếu có lỗi khi cập nhật database
     */
    public int updatePerson(Person person) throws SQLException {
        // Chuẩn bị câu lệnh SQL UPDATE
        String sql = "UPDATE Persons SET name = ?, age = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        // Set các giá trị parameter
        pstmt.setString(1, person.getName());
        pstmt.setInt(2, person.getAge());
        pstmt.setInt(3, person.getId());
        
        // Thực thi câu lệnh UPDATE và lấy số dòng bị ảnh hưởng
        int rows = pstmt.executeUpdate();
        
        // Đóng PreparedStatement
        pstmt.close();
        return rows;
    }
    
    /**
     * Xóa một Person khỏi database theo ID
     * @param id ID của Person cần xóa
     * @throws SQLException nếu có lỗi khi xóa từ database
     */
    public void delete(int id) throws SQLException {
        // Chuẩn bị câu lệnh SQL DELETE
        String sql = "DELETE FROM Persons WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        // Set giá trị parameter
        pstmt.setInt(1, id);
        
        // Thực thi câu lệnh DELETE
        pstmt.executeUpdate();
        
        // Đóng PreparedStatement
        pstmt.close();
     }
}
