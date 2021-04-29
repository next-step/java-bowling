package bowling.domain;

public abstract class Frame {

    private static final int NOT_YET_START = 0;

    protected Round round;
    protected Pins pins;

    public Frame(Round round) {
        this.round = round;
        this.pins = new Pins();
    }

    public boolean isLastRound() {
        return round.isFinalRound();
    }

    public void throwBall(int hitCount) {
        validateTry();
        validateHitCount(hitCount);
        pins.add(new Pin(hitCount));
    }

    public boolean isNotYetStart() {
        return pins.tryCount() == NOT_YET_START;
    }

    public Pins pins() {
        return pins;
    }

    public abstract int getScore();

    public abstract boolean roundEnded();

    protected abstract void validateTry();

    protected abstract void validateHitCount(int hitCount);
}
