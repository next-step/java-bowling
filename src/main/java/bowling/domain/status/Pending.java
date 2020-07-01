package bowling.domain.status;

public class Pending extends Running {
    int firstPin;

    public Pending() {}

    public Pending(int firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public String printResult() {
        return isGutter(firstPin);
    }
}
