/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBContext class cung cấp kết nối đến cơ sở dữ liệu SQL Server.
 * Class này đóng vai trò là lớp cơ sở cho các lớp DAO khác,
 * cung cấp connection để thực hiện các thao tác với database.
 * 
 * @author hiepn
 */
public class DBContext {
    /**
     * Đối tượng Connection để kết nối với database
     */
    protected Connection connection;
    
    /**
     * Constructor khởi tạo kết nối đến database
     * Sử dụng SQL Server Authentication với các thông tin cố định
     */
    public DBContext()
    {
        try {
            String user = "sa";
            String pass = "sa";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Party";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
