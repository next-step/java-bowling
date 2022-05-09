package bowling.domain.frame.info;

public class NormalFrameInfo extends FrameInfo {

    private static final int MAX_NORMAL_FRAME = 8;
    public static final int NEXT_ROUND = 1;

    private NormalFrameInfo(int frameNumber, int round) {
        super(frameNumber, round);
    }

    public static NormalFrameInfo create() {
        return of(START_ROUND, START_ROUND);
    }

    public static NormalFrameInfo of(int frameNumber, int round) {
        return new NormalFrameInfo(frameNumber, round);
    }

    @Override
    public NormalFrameInfo nextFrame() {
        return of(this.frameNumber() + NEXT_ROUND, START_ROUND);
    }

    @Override
    public NormalFrameInfo nextRound() {
        return of(this.frameNumber(), this.round() + NEXT_ROUND);
    }

    @Override
    public boolean isSecondRound() {
        return this.round() == SECOND_ROUND;
    }

    @Override
    public boolean hasNextRound() {
        return isSecondRound();
    }

    @Override
    public boolean isFrameEnd() {
        return this.frameNumber() == MAX_NORMAL_FRAME;
    }

}
