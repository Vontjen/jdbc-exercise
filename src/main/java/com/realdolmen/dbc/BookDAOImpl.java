package com.realdolmen.dbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    @Override
    public Book findBook(Long id) {

        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT id,isbn,  title, author, category FROM book WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Book book = new Book();
            if (resultSet.next()) {
                book = new Book(resultSet.getInt("id"), resultSet.getInt("isbn"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("category"));
            }

            return book;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    // TODO: 7/08/2017
    @Override
    public List<Book> findByISBN(int isbn) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, isbn, title, author, category FROM book WHERE isbn LIKE ?");
            statement.setString(1,"%"+isbn+"%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()){
                books.add(new Book(resultSet.getInt("id"), resultSet.getInt("isbn"),resultSet.getString("title"), resultSet.getString("author"),resultSet.getString("category")));

            }

            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT id, isbn,  title, author, category FROM book WHERE title LIKE ?");

            statement.setString(1, title + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"), resultSet.getInt("isbn"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("category")));
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

            PreparedStatement statement = connection.prepareStatement("select id,isbn, title, author, category FROM book WHERE author like ?");
            statement.setString(1, author + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"), resultSet.getInt("isbn"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("category")));
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
            ResultSet resultSet = statement.executeQuery("SELECT id,isbn,  title, author, category FROM book");
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"), resultSet.getInt("isbn"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("category")));
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

            PreparedStatement statement = connection.prepareStatement("INSERT INTO book (id,isbn, title, author, category) VALUES (?,?,?,?,?)");

            statement.setInt(1, b.getId());
            statement.setInt(2, b.getIsbn());
            statement.setString(3, b.getTitle());
            statement.setString(4, b.getAuthor());
            statement.setString(5,b.getCategory());
            statement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int updateBook(Book b) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE book SET isbn=?, title =?, author =?, category=? WHERE id = ? ");
            statement.setInt(1, b.getIsbn());
            statement.setString(2, b.getTitle());
            statement.setString(3, b.getAuthor());
            statement.setString(4,b.getCategory());
            statement.setInt(5, b.getId());
            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteBook(Book b) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE id=?");
            statement.setInt(1, b.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Book> findByCategory(String category) {
        try (Connection connection = ConnectionFactory.INSTANCE.getConnection()) {

            PreparedStatement statement = connection.prepareStatement("select id, isbn, author, title, category FROM book WHERE category LIKE ?");
            statement.setString(1,"%"+category+"%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while(resultSet.next()){
                books.add(new Book(resultSet.getInt("id"),resultSet.getInt("isbn"),resultSet.getString("author"), resultSet.getString("title"),resultSet.getString("category")));


            }

            return books;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
