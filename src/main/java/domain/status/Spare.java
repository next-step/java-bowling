package domain.status;

public class Spare extends FrameFinished {
    protected Spare(int pin) {
        super(pin);
    }

    @Override
    public String toString() {
       return "/";
    }
}