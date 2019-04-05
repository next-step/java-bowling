package domain.status;

public class Strike extends FrameFinished {
    protected Strike(int pin) {
        super(pin);
    }

    @Override
    public String toString() {
        return "X";
    }
}