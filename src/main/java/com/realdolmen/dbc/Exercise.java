package com.realdolmen.dbc;

import java.sql.*;

public class Exercise {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root")){

            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS book");
            statement.execute("CREATE TABLE IF NOT EXISTS book (id INTEGER NOT NULL, isbn INTEGER NOT NULL, title VARCHAR(255), author VARCHAR(255),category VARCHAR(255), PRIMARY KEY(id) )");
            statement.execute("INSERT INTO book VALUES (1,25478997, 'The Expanse', 'James SA Corey', 'fantasy') ");
            statement.execute("INSERT INTO book VALUES (2,21778999,'Game of Thrones','GRR Martin','fantasy')");

            ResultSet rs = statement.executeQuery("SELECT id, title, author FROM books.book");

            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.printf("%d %s %s%n", id, title, author);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
