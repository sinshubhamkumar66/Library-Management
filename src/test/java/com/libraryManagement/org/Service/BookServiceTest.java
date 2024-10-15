package com.libraryManagement.org.Service;

import com.libraryManagement.org.Entity.Book;
import com.libraryManagement.org.Repo.BookRepository;
import com.libraryManagement.org.ServiceImpl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;
    private  Book book1;
    private  Book book2;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        book1 = new Book( 1L,"1984", "George Orwell");
        book2 = new Book( 2L, "To Kill a Mockingbird", "Harper Lee");
    }


    @Test
    void saveBookTest()
    {
        List<Book> bookList = Arrays.asList(book1, book2);
        Mockito.when(bookRepository.saveAll(any(List.class))).thenReturn(bookList);
        List<Book> savedBooks = bookService.saveBook(bookList);
        assertEquals(2, savedBooks.size());
        verify(bookRepository, times(1)).saveAll(bookList);

    }
    @Test
    void testSaveBook_NullList() {
        // Arrange: Set the input to null, which should cause an IllegalArgumentException
        List<Book> nullBookList=null;

        // Act & Assert: Expect an IllegalArgumentException to be thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.saveBook(nullBookList);  // This is where we expect the exception to be thrown
        });

        // Check that the exception message is what we expect
        assertEquals("Book list cannot be null", exception.getMessage());
        verify(bookRepository, never()).saveAll(any());
    }
    @Test
    void testGetAllBook(){
        List<Book> bookList = Arrays.asList(book1, book2);
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        bookService.getAllBook(book1);
        assertEquals(2, bookList.size());
        verify(bookRepository, times(1)).findAll();

    }
    @Test
    void testGetById(){
        Long id = 1L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.ofNullable(book1));
        bookService.getBookById(id);
        assertEquals(1, book1.getId());
        verify(bookRepository, times(1)).findById(id);
    }
    @Test
    void testDeleteById(){
        Long bookId = 1L;
        doNothing().when(bookRepository).deleteById(bookId);
        bookService.deleteBookById(bookId);
        verify(bookRepository, times(1)).deleteById(bookId);
    }
    @Test
    void testDeleteAll(){
        doNothing().when(bookRepository).deleteAll();
        bookService.deleteAllBook();
        verify(bookRepository, times(1)).deleteAll();
    }


}
