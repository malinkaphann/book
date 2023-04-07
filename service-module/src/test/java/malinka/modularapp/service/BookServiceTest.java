package malinka.modularapp.service;

import malinka.modularapp.common.exception.ResourceNotFoundException;
import malinka.modularapp.dao.BookDao;
import malinka.modularapp.entity.Book;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * This is to test the book service class.
 * The test cases are executed in order.
 *
 * @author Malinka Phann
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceTest {

    private static BookService bookService;

    private static BookDao bookDao;

    @BeforeClass
    public static void before() {
        bookDao = Mockito.mock(BookDao.class);
        bookService = new BookServiceImpl(bookDao);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test001_givenANotFoundBookId_whenFindById_thenAResourceNotFoundExceptionIsThrown() {
        when(bookDao.findById(any(Long.class))).thenReturn(Optional.empty());
        bookService.findById(1L);
    }

    @Test
    public void test002_givenAGoodBookId_whenFindById_thenABookIsReturned() {
        when(bookDao.findById(any(Long.class))).thenReturn(Optional.of(new Book(1L, "book1")));
        Book book = bookService.findById(1L);
        assertNotNull(book);
        assertEquals("book1", book.getTitle());
    }

    @Test
    public void test003_givenNothing_whenFindAll_thenReturnAllBooks() {
        when(bookDao.findAll()).thenReturn(
                List.of(new Book(1L, "book1"),
                        new Book(2L, "book2"),
                        new Book(2L, "book3"))
        );
        List<Book> books = bookService.findAll();
        assertEquals("Book Size: ", 3, books.size());
    }
}
