package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class BonusFrame extends Frame {
    private static final int TRY_ONLY_ONE = 1;

    public BonusFrame() {
        super(TRY_ONLY_ONE);
    }

    @Override
    protected void assignScore(int hits) {
        frameScore.markFirst(hits);
    }
}
