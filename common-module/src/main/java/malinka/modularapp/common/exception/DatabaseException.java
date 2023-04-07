package malinka.modularapp.common.exception;

/**
 * When error comes from database.
 *
 * @author Malinka Phann
 */
public class DatabaseException extends GeneralException {

    public DatabaseException(Throwable t) {
        super(t);
    }

    public DatabaseException(String msg) {
        super(msg);
    }

    public DatabaseException(String msg, Throwable t) {
        super(msg, t);
    }
}
