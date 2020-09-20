package bowling.exception;

public class NoLeftDeliveryException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "더이상 투구를 할 수 없습니다.";

    public NoLeftDeliveryException() {
        super(ERROR_MESSAGE);
    }

}
