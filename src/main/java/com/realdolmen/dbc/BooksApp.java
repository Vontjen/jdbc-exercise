package com.realdolmen.dbc;

import java.util.List;

public class BooksApp {
    public static void main(String[] args) {
        BookDAO dao = new BookDAOImpl();
        //dao.createBook(new Book(4, "Game of Cards", "Mad Hatter"));

        //List<Book> books = dao.findByTitle("game");
        //books.forEach(System.out::println);
        //System.out.println(dao.findBook(4l));


    }
}
