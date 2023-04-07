package malinka.modularapp.common.util;

/**
 * Api status codes.
 *
 * @author Malinka Phann
 */
public enum STATUS {
    SUCCESS(0),
    FAIL(100),
    ERROR_DATABASE(101),
    ERROR_VALIDATION(102),
    ERROR_NOT_FOUND(103),
    ERROR_DUPLICATED(104),
    ERROR_NOTHING_UPDATED(105);
    private final int status;
    private STATUS (int status) {
        this.status = status;
    }
    public int getValue() {
        return this.status;
    }
}
