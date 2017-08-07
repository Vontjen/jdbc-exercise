package com.realdolmen.dbc;


import java.util.List;

public interface BookDAO {

    Book findBook(Long id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor (String author);

    //List<Book> findAllBooks();

    void createBook(Book b);

    //int updateBook(Book b);

    //int deleteBook(Book b);

}

