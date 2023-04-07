package malinka.modularapp.service;

import malinka.modularapp.dao.BookDao;
import malinka.modularapp.hibernate.dao.BookDaoImpl;
import malinka.modularapp.hibernate.dao.HibernateUtil;
import javax.persistence.EntityManager;
import java.util.Objects;

/**
 * This is the factory used to create the book service instance.
 *
 * @author Malinka Phann
 */
public class BookServiceFactory {

    private static BookService service = null;

    /**
     * Return a single book service at all times.
     *
     * @return BookService a book service
     */
    public static BookService getService() {
        if (Objects.isNull(service)) {
            EntityManager em = HibernateUtil.getEntityManager("PERSISTENCE");
            BookDao dao = new BookDaoImpl(em);
            service = new BookServiceImpl(dao);
        }
        return service;
    }
}
