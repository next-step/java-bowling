package domain.status;

public class Miss extends FrameFinished {
    protected Miss(int pin) {
        super(pin);
    }

    @Override
    public String toString() {
        return "-";
    }
}