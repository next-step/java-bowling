package bowling.domain.exceptions;

public class InvalidBonusScoreArgumentException extends RuntimeException {
    public InvalidBonusScoreArgumentException(String message) {
        super(message);
    }
}
