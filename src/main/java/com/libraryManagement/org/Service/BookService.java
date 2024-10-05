package com.libraryManagement.org.Service;


import com.libraryManagement.org.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface BookService {
    public List<Book> saveBook(List<Book> book);
    public List<Book> getAllBook(Book book);
    public Optional<Book> getBookById(Long Id);
    public void deleteBookById(Long Id);
    public void deleteAllBook();
}
