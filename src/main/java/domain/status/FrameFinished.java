package domain.status;

public abstract class FrameFinished extends Status {

    protected FrameFinished(int pin) {
        super(pin);
    }

    @Override
    public boolean isFrameFinished() {
        return true;
    }

    @Override
    public Status getNext(int pin) {
        throw new UnsupportedOperationException();
    }
}