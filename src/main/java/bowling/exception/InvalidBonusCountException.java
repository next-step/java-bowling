package bowling.exception;

public class InvalidBonusCountException extends RuntimeException {

    private static final String INVALID_BONUS_COUNT_MESSAGE_FORMAT = "유효한 보너스 값이 아닙니다 : %d";

    public InvalidBonusCountException(int decreasedRemain) {
        super(String.format(INVALID_BONUS_COUNT_MESSAGE_FORMAT, decreasedRemain));
    }

}
