package bowling;

public class FinalFrame extends Frame {

    private boolean isNext;

    public FinalFrame(int index) {
        this.index = index;
    }

    @Override
    Frame next() {
        if (this.index >= 11) {
            return null;
        }

        if (this.isStrike() || this.isSpare()) {
            return new FinalFrame(11);
        }

        return null;
    }
}
