package malinka.modularapp.hibernate.dao;

import malinka.modularapp.common.exception.DatabaseException;
import malinka.modularapp.common.exception.NothingUpdatedException;
import malinka.modularapp.common.exception.ResourceNotFoundException;
import malinka.modularapp.common.exception.ValidationException;
import malinka.modularapp.common.util.ValidationUtil;
import malinka.modularapp.dao.BookDao;
import malinka.modularapp.entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This is the implementation of the book dao.
 *
 * @author Malinka Phann
 */
public class BookDaoImpl implements BookDao {

    private final EntityManager em;

    public BookDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Find all books.
     *
     * @throws DatabaseException when error comes from database
     * @return List a list of books
     */
    @Override
    public List<Book> findAll() {
        List<Book> books;
        try {
            books = em.createQuery("SELECT b FROM Book b",
                    Book.class).getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return books;
    }

    /**
     * Find all books whose column's value matches the given value.
     *
     * @param key a column name
     * @param value a value of that column to look for
     * @throws NullPointerException when the input param is null
     * @throws ValidationException when the input param is empty
     * @throws DatabaseException when error comes from database
     * @return List a list of books
     */
    @Override
    public List<Book> findAll(final String key, final String value) {
        Objects.requireNonNull(key, "the input key must not be null");
        Objects.requireNonNull(value, "the input value must not be null");
        if(key.isEmpty()) throw new ValidationException("the input key must not be empty");
        if (value.isEmpty()) throw new ValidationException("the input value must not be empty");
        List<Book> books;
        try {
            TypedQuery<Book> query = em.createQuery(
                    String.format("SELECT b FROM Book b WHERE b.%s = ?1", key),
                        Book.class);
            query.setParameter(1, value);
            books = query.getResultList();
        }catch(Exception e) {
            throw new DatabaseException(e);
        }
        return books;
    }

    /**
     * Find a book by id.
     *
     * @param id an id of a book
     * @throws NullPointerException when the given id is null
     * @throws ValidationException when id is negative
     * @throws DatabaseException when error comes from database
     * @return Optional an optional of a book
     */
    @Override
    public Optional<Book> findById(final Long id) {
        Objects.requireNonNull(id, "book id must not be null");
        if(id <= 0) throw new ValidationException("book id must be a positive number");
        Optional<Book> optionalBook;
        try {
            Book book = em.find(Book.class, id);
            optionalBook = Objects.isNull(book) ? Optional.empty() : Optional.of(book);
        } catch(Exception e) {
            throw new DatabaseException(e);
        }
        return optionalBook;
    }

    /**
     * Create a new book.
     *
     * @param book a book to insert
     * @throws NullPointerException when the given id is null
     * @throws ValidationException when title is either null, empty or too long
     * @throws DatabaseException when error comes from database
     * @return Book a book that is just created
     */
    @Override
    public Book insert(final Book book) {
        Objects.requireNonNull(book, "the input book must not be null");
        ValidationUtil.checkNull("title", book.getTitle());
        ValidationUtil.checkEmpty("title", book.getTitle());
        ValidationUtil.checkMaxLen("title", book.getTitle(), Book.MAX_LEN_TITLE);
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.flush();
            em.getTransaction().commit();
        } catch(Exception e) {
            throw new DatabaseException(e);
        }
        return book;
    }

    /**
     * Update an existing book.
     * The update only happens when the given title is valid.
     *
     * @param id the id of the book
     * @param book the new value of book
     * @throws NullPointerException when the given id is null
     * @throws NothingUpdatedException when there is nothing to update
     * @throws ValidationException when title is invalid
     * @throws ResourceNotFoundException when book id is not found
     * @throws DatabaseException when error comes from database
     * @return Book a book that is just updated
     */
    @Override
    public Book update(Long id, Book book) {
        Objects.requireNonNull(id, "the input id must not be null");
        Objects.requireNonNull(book, "the input book must not be null");
        if (id <= 0) throw new ValidationException("the input id must be a positive number");
        if (Objects.isNull(book.getTitle())) {
            throw new NothingUpdatedException("nothing to update");
        }
        ValidationUtil.checkEmpty("title", book.getTitle());
        ValidationUtil.checkMaxLen("title", book.getTitle(), Book.MAX_LEN_TITLE);
        try {
            em.getTransaction().begin();
            Book found = em.find(Book.class, id);
            if (Objects.isNull(found)) {
                em.getTransaction().rollback();
                throw new ResourceNotFoundException(String.format(
                        "book id = %d is not found", id));
            }
            found.setTitle(book.getTitle());
            em.getTransaction().commit();
        } catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e);
        } catch(Exception e) {
            throw new DatabaseException(e);
        }
        return book;
    }

    /**
     * Delete a book.
     *
     * @param id the id of a book
     * @throws NullPointerException when the given id is null
     * @throws ValidationException when the given id is negative
     * @throws ResourceNotFoundException when book id is not found
     * @throws DatabaseException when error comes from database
     * @return Book a book that is just deleted
     */
    @Override
    public Book delete(final Long id) {
        Objects.requireNonNull(id, "the input id must not be null");
        if (id <= 0) throw new ValidationException("the input id must be a positive number");
        Book bookToDelete;
        try {
            em.getTransaction().begin();
            bookToDelete = em.find(Book.class, id);
            if (Objects.isNull(bookToDelete)) {
                em.getTransaction().rollback();
                throw new ResourceNotFoundException(String.format(
                        "book id = %d is not found", id));
            }
            em.remove(bookToDelete);
            em.getTransaction().commit();
        } catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e);
        } catch(Exception e) {
            throw new DatabaseException(e);
        }
        return bookToDelete;
    }
}
