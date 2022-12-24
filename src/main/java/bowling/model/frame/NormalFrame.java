package bowling.model.frame;


public class NormalFrame extends AbstractFrame {

    public static final int LAST_FRAME_NUMBER = 9;
    public static final int FIRST_FRAME_NUMBER = 1;

    public NormalFrame(int number) {
        super(number);
    }

    public static NormalFrame first() {
        return new NormalFrame(FIRST_FRAME_NUMBER);
    }

    @Override
    public boolean isFinished() {
        return getCurrentState().isFinished();
    }

    @Override
    public Frame nextFrame() {
        if (isLastFrame()) {
            return new FinalFrame(number + 1);
        }
        return new NormalFrame(number + 1);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    private boolean isLastFrame() {
        return number == LAST_FRAME_NUMBER;
    }
}
