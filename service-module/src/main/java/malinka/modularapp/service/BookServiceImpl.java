package malinka.modularapp.service;

import malinka.modularapp.common.exception.ResourceNotFoundException;
import malinka.modularapp.dao.BookDao;
import malinka.modularapp.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * This is the implementation of a book service.
 *
 * @author Malinka Phann
 */
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /**
     * Find by id.
     *
     * @param id an id of the book
     * @throws ResourceNotFoundException no book is found by that id
     * @return Book a book
     */
    public Book findById(final Long id) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException(String.format(
                    "book id = %d is not found", id));
        }
        return optionalBook.get();
    }

    /**
     * Find all.
     *
     * @return List a list of books
     */
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    /**
     * Create a new book.
     *
     * @param book a book to create
     * @return Book a newly-created book
     */
    public Book create(final Book book) {
        return bookDao.insert(book);
    }

    /**
     * Update a book.
     *
     * @param id id of a book
     * @param book new values of the book
     * @return Book an updated book
     */
    @Override
    public Book update(final Long id, final Book book) {
        return bookDao.update(id, book);
    }

    /**
     * Delete a book.
     *
     * @param id an id of the book
     * @return Book a book that is just deleted
     */
    @Override
    public Book remove(final Long id) {
        return bookDao.delete(id);
    }
}
