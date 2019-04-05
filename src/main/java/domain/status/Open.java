package domain.status;

public class Open extends FrameFinished {
    protected Open(int pin) {
        super(pin);
    }

    @Override
    public String toString() {
        return "" + pin;
    }
}