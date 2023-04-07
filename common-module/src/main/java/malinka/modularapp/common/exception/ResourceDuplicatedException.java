package malinka.modularapp.common.exception;

/**
 * When something was already taken.
 *
 * @author Malinka Phann
 */
public class ResourceDuplicatedException extends GeneralException {

    public ResourceDuplicatedException(Throwable t) {
        super(t);
    }

    public ResourceDuplicatedException(String msg) {
        super(msg);
    }

    public ResourceDuplicatedException(String msg, Throwable t) {
        super(msg, t);
    }
}
