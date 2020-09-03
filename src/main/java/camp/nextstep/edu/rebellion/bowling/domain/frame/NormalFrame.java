package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class NormalFrame extends Frame {
    private static final int TRY_TWICE = 2;

    public NormalFrame() {
        super(TRY_TWICE);
    }

    @Override
    protected void assignScore(int hits) {
        if (attempt.isFirstAttempt()) {
            frameScore.markFirst(hits);
            return;
        }
        frameScore.markLast(hits);
    }
}
