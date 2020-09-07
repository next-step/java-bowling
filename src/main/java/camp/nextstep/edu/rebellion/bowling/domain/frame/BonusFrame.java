package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class BonusFrame extends Frame {

    public BonusFrame(int initAttemptCount) {
        super(initAttemptCount);
    }

    @Override
    protected void assignScore(int hits) {
        frameScore.markFirst(hits);
    }
}
