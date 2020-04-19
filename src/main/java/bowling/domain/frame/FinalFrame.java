package bowling.domain.frame;

public class FinalFrame extends Frame {
    private static final int NONE_TRY_COUNT = 0;
    private static final int FIRST_TRY_COUNT = 1;
    private static final int SECOND_TRY_COUNT = 2;

    public FinalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        if (points.isTryCount(NONE_TRY_COUNT) || points.isTryCount(FIRST_TRY_COUNT)) {
            return true;
        }
        if (points.isTryCount(SECOND_TRY_COUNT) && (points.getFirstPoint().isMaxPoint() || points.isSpare() || points.isDoubleStrike())) {
            return true;
        }
        return false;
    }

    @Override
    public void addScore() {
        if (points.isFirstStrike() || points.isSpare()) {
            this.score = points.makeScore();
        }
    }
}
