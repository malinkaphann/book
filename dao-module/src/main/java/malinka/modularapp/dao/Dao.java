package malinka.modularapp.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * This is the interface of the data access object.
 *
 * @param <T> entity class
 * @param <ID> id class
 *
 * @author Malinka Phann
 */
public interface Dao<T, ID extends Serializable> {
    List<T> findAll();
    Optional<T> findById(ID id);
    List<T> findAll(String key, String value);
    T insert(T object);
    T update(ID id, T object);
    T delete(ID id);
}
