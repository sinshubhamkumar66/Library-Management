package com.libraryManagement.org.Controller;

import com.libraryManagement.org.Entity.Book;
import com.libraryManagement.org.Exception.BookNotFoundException;
import com.libraryManagement.org.Exception.ErrorResponse;
import com.libraryManagement.org.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing books in the library.
 * Provides endpoints for adding, retrieving, and deleting books.
 */
@RestController
@RequestMapping("/api/v1/book")
@Tag(name = "Api for Book(CREATE, READ, GET, DELETE")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Save a list of books to the library.
     *
     * @param books List of books to be saved
     * @return Response entity containing the saved books and HTTP status
     */
    @Operation(summary = "SAVE BOOK DETAILS")
    @PostMapping("/saveBooks")
    public ResponseEntity<?> saveBooks(@RequestBody List<Book> books) {
        try {
            if (books == null || books.isEmpty()) {
                throw new IllegalArgumentException("Book list cannot be empty");
            }
            List<Book> savedBooks = bookService.saveBook(books);
            return new ResponseEntity<>(savedBooks, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (BookNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Request body is missing or invalid. Please provide valid data."), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while saving books"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve a specific book by its ID.
     *
     * @param id The ID of the book to retrieve
     * @return Response entity containing the book and HTTP status
     */
    @Operation(summary = "Get Book By Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Optional<Book> book = bookService.getBookById(id);
            if (book.isEmpty()) {
                throw new BookNotFoundException("Book not found with id: " + id);
            }
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } catch (BookNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while retrieving the book"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve all books from the library.
     *
     * @return Response entity containing the list of books and HTTP status
     */
    @Operation(summary = "get All Books")
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBook(null); // Removed unused parameter
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while retrieving books"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a specific book by its ID.
     *
     * @param id The ID of the book to delete
     * @return Response entity with HTTP status
     */
    @Operation(summary = "Delete Book By Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            Optional<Book> book = bookService.getBookById(id);
            if (book.isEmpty()) {
                throw new BookNotFoundException("Book not found with id: " + id);
            }
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BookNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while deleting the book"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete all books from the library.
     *
     * @return Response entity with HTTP status
     */
    @Operation(summary = "Delete All book")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllBooks() {
        try {
            // Perform the deletion
            bookService.deleteAllBook();
            // Return a response indicating no content (204 No Content) after successful deletion
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            // Handle any general exception and return an internal server error
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while deleting books"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
