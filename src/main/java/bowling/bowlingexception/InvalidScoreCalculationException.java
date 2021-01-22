package bowling.bowlingexception;

public class InvalidScoreCalculationException extends IllegalArgumentException {
    public InvalidScoreCalculationException() {
        super("스코어 계산이 불가능한 조건입니다.");
    }
}
