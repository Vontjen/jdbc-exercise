package com.realdolmen.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by vdabcursist on 07/08/2017.
 */
public class Exercise {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root")){

            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS book (id INTEGER NOT NULL, title VARCHAR(255), author VARCHAR(255), PRIMARY KEY(id) )");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
