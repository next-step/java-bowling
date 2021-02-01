package bowling.bowlingexception;

public class InvalidLeftChanceException extends IllegalArgumentException {

    public InvalidLeftChanceException() {
        super("Score 의 보너스는 0 이상 2 이하의 범위를 가집니다");
    }
}
