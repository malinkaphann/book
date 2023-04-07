package malinka.modularapp.service;

import malinka.modularapp.entity.Book;
import java.util.List;

/**
 * This is the interface of a book service.
 *
 * @author Malinka Phann
 */
public interface BookService {
    Book findById(Long id);
    List<Book> findAll();
    Book create(Book book);
    Book update(Long id, Book book);
    Book remove(Long id);
}
