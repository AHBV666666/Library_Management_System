package Library_Management_System;

import java.sql.*;

public class MemberRepository {

    public Member addMember(Member member) {
        String query = "INSERT INTO member(full_name,phone) VALUES (?,?)";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setString(2, member.getPhone());
            preparedStatement.executeUpdate();
            return member;
        }catch (SQLException e) {
            throw new RuntimeException("Error in adding member!", e);
        }
    }

    public void findMemberById(int id) throws MemberNotFoundException {
        String query = "SELECT * FROM member WHERE id=?";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("full_name"));
                    System.out.println(resultSet.getString("phone"));
                    System.out.println("--------------------");
                } else {
                    throw new MemberNotFoundException(
                            "Member id : "+ id +" not found!"
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error!",e);
        }
    }

    public void deleteMember(int id) throws MemberNotFoundException {
        String query = "DELETE FROM member WHERE id=?";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new MemberNotFoundException(
                        "Member id : " + id + " not found!"
                );
            }
            System.out.println("Id " + id + " deleted!");
        } catch (SQLException e) {
            throw new RuntimeException("Database error.", e);
        }
    }
}