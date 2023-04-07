package malinka.modularapp.hibernate.dao;

import malinka.modularapp.common.exception.NothingUpdatedException;
import malinka.modularapp.common.exception.ResourceNotFoundException;
import malinka.modularapp.common.exception.ValidationException;
import malinka.modularapp.dao.BookDao;
import malinka.modularapp.entity.Book;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import javax.persistence.EntityManager;
import java.util.Optional;
import static org.junit.Assert.*;

/**
 * Test book data access object
 *
 * @author Malinka Phann
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookDaoTest {
	private static BookDao bookDao;

	private final String LONG_TITLE = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

	@BeforeClass
	public static void before() {
		EntityManager em = HibernateUtil.getEntityManager("TEST");
		bookDao = new BookDaoImpl(em);
	}

	@AfterClass
	public static void tearDown() {
		HibernateUtil.shutdown();
	}

	@Test(expected = NullPointerException.class)
	public void test001_givenANullObject_whenCreateBook_thenANullPointerExceptionIsThrown() {
		bookDao.insert(null);
	}

	@Test(expected = ValidationException.class)
	public void test002_givenANullTitle_whenCreateBook_thenAValidationExceptionIsThrown() {
		bookDao.insert(new Book(1L, null));
	}

	@Test(expected = ValidationException.class)
	public void test003_givenAnEmptyTitle_whenCreateBook_thenAValidationExceptionIsThrown() {
		bookDao.insert(new Book(1L, ""));
	}

	@Test(expected = ValidationException.class)
	public void test004_givenAVeryLongTitle_whenCreateBook_thenAValidationExceptionIsThrown() {
		bookDao.insert(new Book(1L, LONG_TITLE));
	}

	@Test
	public void test005_givenABookDetail_whenCreateBook_thenABookIsCreated() {
		Book book = bookDao.insert(Book.builder().title("book1").build());
		assertNotNull(book.getId());
		assertEquals("book1", book.getTitle());
	}

	@Test(expected = NullPointerException.class)
	public void test006_givenANullObject_whenUpdateBook_thenANullPointerExceptionIsThrown() {
		bookDao.update(1L, null);
	}

	@Test(expected = NothingUpdatedException.class)
	public void test007_givenANullTitle_whenUpdateBook_thenANothingUpdatedExceptionIsThrown() {
		bookDao.update(1L, Book.builder().title(null).build());
	}

	@Test(expected = NothingUpdatedException.class)
	public void test008_givenANullTitle_whenUpdateBook_thenANothingUpdatedExceptionIsThrown() {
		bookDao.update(1L, Book.builder().title(null).build());
	}

	@Test(expected = ValidationException.class)
	public void test009_givenAnEmptyTitle_whenUpdateBook_thenAValidationExceptionIsThrown() {
		bookDao.update(1L, Book.builder().title("").build());
	}

	@Test(expected = ValidationException.class)
	public void test010_givenAVeryLongTitle_whenUpdateBook_thenAValidationExceptionIsThrown() {
		bookDao.update(1L, Book.builder().title(LONG_TITLE).build());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void test011_givenANotFoundBookId_whenUpdateBook_thenAResourceNotFoundExceptionIsThrown() {
		bookDao.update(111L, Book.builder().title("book1").build());
	}

	@Test
	public void test012_givenAGoodObject_whenUpdateBook_thenBookIsUpdated() {
		Book newValue = new Book(1L, "book2 updated");
		Book updatedBook = bookDao.update(1L, newValue);
		assertEquals("book2 updated", updatedBook.getTitle());
	}

	@Test(expected = NullPointerException.class)
	public void test013_givenANullBookId_whenFindById_thenANullPointerExceptionIsThrown() {
		bookDao.findById(null);
	}

	@Test(expected = ValidationException.class)
	public void test014_givenANegativeBookId_whenFindById_thenAValidationExceptionIsThrown() {
		bookDao.findById(-1L);
	}

	@Test
	public void test015_givenAGoodBookId_whenFindById_thenABookIsReturned() {
		Book book = bookDao.insert(Book.builder().title("book3").build());
		assertNotNull(book.getId());
		Optional<Book> found = bookDao.findById(book.getId());
		assertTrue(found.isPresent());
		assertEquals("book3", found.get().getTitle());
	}

	@Test(expected = NullPointerException.class)
	public void test016_givenANullBookId_whenDelete_thenANullPointerExceptionIsThrown() {
		bookDao.delete(null);
	}

	@Test(expected = ValidationException.class)
	public void test017_givenANegativeBookId_whenDelete_thenAValidationExceptionIsThrown() {
		bookDao.delete(-1L);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void test018_givenANotFoundBookId_whenDelete_thenAResourceNotFoundExceptionIsThrown() {
		bookDao.delete(111L);
	}

	@Test
	public void test019_givenAGoodBookId_whenDelete_thenBookIsDeleted() {
		Book book = bookDao.delete(1L);
		assertEquals("Book Id: ", 1L, book.getId().longValue());
	}
}
