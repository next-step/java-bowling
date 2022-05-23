package bowling.exception;

public class BonusCountNullPointerException extends RuntimeException {

    private static final String BONUS_CONT_NULL_MESSAGE = "BonusCount 가 null 입니다.";

    public BonusCountNullPointerException() {
        super(BONUS_CONT_NULL_MESSAGE);
    }

}
