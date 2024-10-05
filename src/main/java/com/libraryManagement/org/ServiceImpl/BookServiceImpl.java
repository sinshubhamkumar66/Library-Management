package com.libraryManagement.org.ServiceImpl;

import com.libraryManagement.org.Entity.Book;
import com.libraryManagement.org.Repo.BookRepository;
import com.libraryManagement.org.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation class for managing books in the library.
 * It provides the business logic for saving, retrieving, and deleting books.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookRepository bookRepo;

    /**
     * Save a list of books to the library.
     *
     * @param book List of books to be saved
     * @return List of books that were saved
     */
    @Override
    public List<Book> saveBook(List<Book> book) {
        return bookRepo.saveAll(book);
    }

    /**
     * Retrieve all books from the library.
     *
     * @param book The book object (not used, can be omitted from method signature)
     * @return List of all books in the library
     */
    @Override
    public List<Book> getAllBook(Book book) {
        return bookRepo.findAll();
    }

    /**
     * Retrieve a specific book by its ID.
     *
     * @param id The ID of the book to retrieve
     * @return Optional containing the book if found, or empty if not found
     */
    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    /**
     * Delete a specific book by its ID.
     *
     * @param id The ID of the book to delete
     */
    @Override
    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }

    /**
     * Delete all books from the library.
     */
    @Override
    public void deleteAllBook() {
        bookRepo.deleteAll();
    }
}
