package bowling.domain.state;

public class InvalidRecordInOrdinaryException extends RuntimeException {
    private static final String MESSAGE = "해당 핀은 입력이 불가능합니다. 현재 입력된 핀 수는 %d";

    public InvalidRecordInOrdinaryException(int falledPins) {
        super(String.format(MESSAGE, falledPins));
    }
}
