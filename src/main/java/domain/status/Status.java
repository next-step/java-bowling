package domain.status;

public abstract class Status {
    protected int pin;

    public Status(int pin) {
        this.pin = pin;
    }

    public abstract boolean isFrameFinished();
    public abstract String toString();
    public abstract Status getNext(int pin);
}