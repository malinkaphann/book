package malinka.modularapp.common.exception;

/**
 * When validation fails
 *
 * @author Malinka Phann
 */
public class ValidationException extends GeneralException {

    public ValidationException(Throwable t) {
        super(t);
    }

    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException(String msg, Throwable t) {
        super(msg, t);
    }
}
