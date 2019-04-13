package bowling.game;

public class FinalFrame implements Frame {
    private final NormalFrame frame;
    private PinScore last;

    public FinalFrame(PinScore pinScore) {
        this.frame = new NormalFrame(pinScore);
    }

    @Override
    public boolean isEnd() {
        if (!this.frame.isEnd()) {
            return false;
        }

        if (this.frame.isMiss()) {
            return true;
        }

        return (null != this.last);
    }

    @Override
    public void pitch(PinScore pinScore) {
        validateState();

        if (!this.frame.isEnd()) {
            this.frame.pitch(pinScore);
            return;
        }

        this.last = pinScore;
    }

    @Override
    public String toString() {
        if (!isEnd()) {
            return frame.toString();
        }

        if (this.frame.isMiss()) {
            return frame.toString();
        }

        return frame.toString() + "|" + this.last;
    }

    private void validateState() {
        if (isEnd()) {
            throw new IllegalStateException("This frame has been ended");
        }
    }
}
