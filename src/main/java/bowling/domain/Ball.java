package bowling.domain;

public class Ball {
    private int knockedDownPinCount;

    private boolean isPitched = false;

    public Ball() {
        this.knockedDownPinCount = 0;
    }

    public Ball(int knockedDownPinCount) {
        this.knockedDownPinCount = knockedDownPinCount;
        this.isPitched = true;
    }

    public void pitch(int knockedDownPinCount) {
        this.knockedDownPinCount = knockedDownPinCount;
        this.isPitched = true;
    }

    public int getKnockedDownPinCount() {
        return knockedDownPinCount;
    }

    public boolean isNotPitched() {
        return !isPitched;
    }

    public boolean isPitched() {
        return isPitched;
    }

}
