package bowling.domain.frame;

public class NormalFrame extends Frame {
    private static final int MAX_TRYABLE_COUNT = 2;

    public NormalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        if (points.isTryOver(MAX_TRYABLE_COUNT) || points.isFirstStrike()) {
            return false;
        }
        return true;
    }

    @Override
    public void addScore() {
        if (!isThrowable()) {
            this.score = points.makeScore();
        }
    }
}
