package bowling.domain;

public class Frame {

    private DownedPinPerTry first;
    private DownedPinPerTry second;

    public Frame() {
    }

    public void record(DownedPinPerTry downedPinPerTry) {
        if (this.first == null) {
            this.first = downedPinPerTry;
            return;
        }

        second = first.fromFirstTry(downedPinPerTry);
    }

    public boolean isEnd() {
        return (first.isStrike() || second != null);
    }
}
