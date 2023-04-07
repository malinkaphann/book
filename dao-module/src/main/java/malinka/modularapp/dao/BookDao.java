package malinka.modularapp.dao;

import malinka.modularapp.entity.Book;

/**
 * This is the book data access object.
 * This interface defines the additional functions
 * added to the default functions provided by the Dao interface.
 *
 * @author Malinka Phann
 */
public interface BookDao extends Dao<Book, Long> {}
