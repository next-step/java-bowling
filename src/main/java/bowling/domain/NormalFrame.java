package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:53
 */
public class NormalFrame {
    private final FrameScore frameScore;
    private final FrameNumber frameNumber;

    public NormalFrame() {
        this.frameScore = new FrameScore();
        this.frameNumber = FrameNumber.init();
    }

    private NormalFrame(FrameNumber frameNumber) {
        this.frameScore = new FrameScore();
        this.frameNumber = frameNumber;
    }

    public boolean isGameOver() {
        return frameNumber.isOver();
    }

    public NormalFrame bowl(int fallCount) {
        boolean nextFrame = frameScore.addPoint(Point.of(fallCount));

        if (nextFrame) {
            return this;
        }
        return nextFrame(fallCount);
    }

    private NormalFrame nextFrame(int fallCount) {
        NormalFrame newFrame = new NormalFrame(frameNumber.next());
        newFrame.bowl(fallCount);

        return newFrame;
    }
}
