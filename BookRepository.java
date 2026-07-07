package Library_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {

    public Book insertBook(Book book) {
        String query = "INSERT INTO book(title,author,price,stock) VALUES (?,?,?,?)";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setDouble(3,book.getPrice());
            preparedStatement.setInt(4,book.getStock());
            preparedStatement.executeUpdate();
            return book;
        } catch (SQLException e) {
            throw new RuntimeException("Error in inserting book!",e);
        }
    }

    public void findBookById(int id) throws BookNotFoundException {
        String query = "SELECT * FROM book WHERE id=?";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("title"));
                    System.out.println(resultSet.getString("author"));
                    System.out.println(resultSet.getDouble("price"));
                    System.out.println(resultSet.getInt("stock"));
                    System.out.println("--------------------");
                } else {
                    throw new BookNotFoundException("Book id : "+ id+ " not found!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error!",e);
        }
    }

    public void updatePrice(int id, double price) throws BookNotFoundException {
        String query = "UPDATE book SET price=? WHERE id=?";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setDouble(1,price);
            preparedStatement.setInt(2,id);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new BookNotFoundException("Book id : "+ id +" not found1");
            }
            System.out.println("Price updated!");
        } catch (SQLException e) {
            throw new RuntimeException("Database error!",e);
        }
    }

    public void deleteBook(int id) throws BookNotFoundException {
        String query = "DELETE FROM book WHERE id=?";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1,id);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new BookNotFoundException("Book id : "+ id +" not found!");
            }
            System.out.println("Id " + id + " deleted!");
        } catch (SQLException e) {
            throw new RuntimeException("Database error!",e);
        }
    }
}