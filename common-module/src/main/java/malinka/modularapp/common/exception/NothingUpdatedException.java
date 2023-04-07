package malinka.modularapp.common.exception;

/**
 * When there is nothing to update
 *
 * @author Malinka Phann
 */
public class NothingUpdatedException extends GeneralException {

    public NothingUpdatedException(Throwable t) {
        super(t);
    }

    public NothingUpdatedException(String msg) {
        super(msg);
    }

    public NothingUpdatedException(String msg, Throwable t) {
        super(msg, t);
    }
}
