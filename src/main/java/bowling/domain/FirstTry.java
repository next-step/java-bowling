package bowling.domain;

public class FirstTry {
    private static final int TEN = 10;

    private final int firstNumber;

    public FirstTry(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public boolean isStrike() {
        return firstNumber == TEN;
    }

}
