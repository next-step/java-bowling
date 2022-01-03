package bowling.exception;

public class MinimumRoundException extends IllegalArgumentException {

    private static final String MESSAGE = "라운드는 최소 %d 이상 이어야 합니다.";

    public MinimumRoundException(int minimumRound) {
        super(String.format(MESSAGE, minimumRound));
    }

}
