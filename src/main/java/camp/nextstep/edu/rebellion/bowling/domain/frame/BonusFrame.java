package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class BonusFrame extends Frame {

    public BonusFrame(int initAttempt) {
        super(initAttempt);
    }

    @Override
    protected void adjustAttempt() {
        attempt.tried();
    }

    @Override
    protected boolean match(FrameType type) {
        return FrameType.BONUS == type;
    }
}
