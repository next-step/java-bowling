package bowling.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomException extends RuntimeException {

    private Logger logger = Logger.getGlobal();

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        logger.log(Level.SEVERE, String.format("{%s : %s}", errorCode.code(), errorCode.message()));
    }

    public ErrorCode errorCode() {
        return this.errorCode;
    }
}
