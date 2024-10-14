package Service;

import com.libraryManagement.org.Entity.Book;
import com.libraryManagement.org.Repo.BookRepository;
import com.libraryManagement.org.ServiceImpl.BookServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    private  Book book1;
    private  Book book2;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
        book1 = new Book( "1984", "George Orwell");
        book2 = new Book( "To Kill a Mockingbird", "Harper Lee");
    }


    @Test
    public void saveBookTest()
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
        List<Book> nullBookList = null;

        // Act & Assert: Expect an IllegalArgumentException to be thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.saveBook(nullBookList);  // This is where we expect the exception to be thrown
        });

        // Check that the exception message is what we expect
        assertEquals("Book list cannot be null", exception.getMessage());
        verify(bookRepository, never()).saveAll(any());
    }


}
