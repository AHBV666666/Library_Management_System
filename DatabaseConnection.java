package Library_Management_System;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL="jdbc:postgresql://localhost:5432/librarymanager";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="@666AhBv666@";
    public static Connection getConnection() throws SQLException{
        Connection connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        System.out.println("Connection Successfully");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT now();");
        if (resultSet.next()){
            System.out.println("Connection Time : "+resultSet.getTime(1));
        }
        return connection;
    }
}