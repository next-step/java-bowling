package bowling.exception;

public class MaximumRoundException extends IllegalArgumentException {

    private static final String MESSAGE = "라운드는 최대 %d까지 존재 합니다.";

    public MaximumRoundException(int maximumRound) {
        super(String.format(MESSAGE, maximumRound));
    }

}
