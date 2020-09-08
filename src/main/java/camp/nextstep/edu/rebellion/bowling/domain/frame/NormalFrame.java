package camp.nextstep.edu.rebellion.bowling.domain.frame;

public class NormalFrame extends Frame {

    public NormalFrame(int initAttempt) {
        super(initAttempt);
    }

    @Override
    protected void adjustAttempt() {
        if (frameScore.isStrike()) {
            attempt.setNoAttempt();
            return;
        }
        attempt.tried();
    }

    @Override
    protected boolean match(FrameType type) {
        return FrameType.NORMAL == type;
    }
}
