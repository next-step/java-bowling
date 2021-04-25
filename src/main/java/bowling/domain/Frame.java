package bowling.domain;

public abstract class Frame {

    private static final String TRY_COUNT_EXCEPTION_MESSAGE = "최대 2번까지 시도할 수 있습니다";

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

    protected void validateTry() {
        if (roundEnded()) {
            throw new IllegalStateException(TRY_COUNT_EXCEPTION_MESSAGE);
        }
    }

    protected abstract void validateHitCount(int hitCount);

    public abstract boolean roundEnded();

    protected abstract boolean isStrike();
}
