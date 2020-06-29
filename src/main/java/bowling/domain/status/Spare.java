package bowling.domain.status;

public class Spare implements Status {
    int beforePin;
    int downPin;

    public Spare(int beforePin, int downPin) {
        this.beforePin = beforePin;
        this.downPin = downPin;
    }

    @Override
    public String printResult() {
        return String.valueOf(isGutter(beforePin));
    }

    @Override
    public String printAllResult() {
        return beforePin + "|/";
    }

}
