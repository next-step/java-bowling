package bowling.domain;

public abstract class Frame {
    protected Fitch fitch;

    public void bowl(Pins pins) {
        fitch.bowl(pins);
    }

    public Pins getFirstPin() {
        return fitch.getFirstPin();
    }

    public Pins getSecondPin() {
        return fitch.getSecondPin();
    }

    public Pins getBonusPin() {
        return fitch.getBonusPin();
    }

    public boolean isFirstFitchDone() {
        return fitch.isFirstFitchDone();
    }

    public boolean isSecondFitchDone() {
        return fitch.isSecondFitchDone();
    }

    public boolean isBonusFitchDone() {
        return fitch.isBonusFitchDone();
    }

    public boolean isFinish() {
        return fitch.isFinish();
    }

    public abstract int getFrameNumber();

    public abstract Frame next();


}
