package bowling2.exception;

// TODO(jack.comeback) : code 추가
public class BowlingException extends RuntimeException {

    public BowlingException() {
    }

    public BowlingException(String message) {
        super(message);
    }
}
