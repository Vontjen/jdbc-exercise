package com.realdolmen.dbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    @Override
    public Book findBook(Long id) {

        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT id, title, author FROM book WHERE id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            Book book = new Book();
            if (resultSet.next()) {
                book = new Book(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("author"));
            }

            return book;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Book> findByTitle(String title) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT id, title, author FROM book WHERE title LIKE ?");

            statement.setString(1, title + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("author")));
            }
            return books;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Book> findByAuthor(String author) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("select id, title, author FROM book WHERE author like ?");
            statement.setString(1, author+"%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()){
                books.add(new Book(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("author")));
            }
            return books;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, title, author FROM book");
            List<Book> books = new ArrayList<>();
            while (resultSet.next()){
                books.add(new Book(resultSet.getInt("id"), resultSet.getString("title"),resultSet.getString("author")));
            }

            return books;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createBook(Book b) {

        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO book (id, title, author) VALUES (?,?,?)");

            statement.setInt(1, b.getId());
            statement.setString(2, b.getTitle());
            statement.setString(3, b.getAuthor());
            statement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int updateBook(Book b) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE book SET title =?, author =? WHERE id = ? ");
            statement.setString(1,b.getTitle());
            statement.setString(2,b.getAuthor());
            statement.setInt(3,b.getId());
            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
