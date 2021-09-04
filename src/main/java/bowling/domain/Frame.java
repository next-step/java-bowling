package bowling.domain;

public abstract class Frame {
    protected Fitch fitch;
    protected States states;

    public void bowl(Pins pins) {
        fitch.bowl(pins);
        states.add(fitch.getState());
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

    public States getStates() {
        return states;
    }

    public abstract int getFrameNumber();

    public abstract Frame next();


}
