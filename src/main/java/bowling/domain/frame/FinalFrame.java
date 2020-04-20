package bowling.domain.frame;

public class FinalFrame extends Frame {
    public FinalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        return points.isThrowableForFinalFrame();
    }

    @Override
    public void addScore() {
        if (points.isFirstStrike() || points.isSpare()) {
            this.score = points.makeScore();
        }
    }
}
