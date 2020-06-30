package bowling.domain.status;

public class Miss implements Status {
    int beforePin;
    int downPin;

    public Miss(int beforePin) {
        this.beforePin = beforePin;
    }

    public Miss(int beforePin, int downPin) {
        this.beforePin = beforePin;
        this.downPin = downPin;
    }

    @Override
    public String printResult() {
        return isGutter(beforePin);
    }

    @Override
    public String printAllResult() {
        return isGutter(beforePin) + "|" + isGutter(downPin);
    }
}
