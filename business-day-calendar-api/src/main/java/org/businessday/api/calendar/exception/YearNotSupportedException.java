package org.businessday.api.calendar.exception;

public class YearNotSupportedException extends Exception {
    public YearNotSupportedException() {
    }

    public YearNotSupportedException(String message) {
        super(message);

    }

    public YearNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public YearNotSupportedException(Throwable cause) {
        super(cause);
    }

    public YearNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
