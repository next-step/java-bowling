package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-13 17:44
 */
public class NormalFrame extends Frame {
    private static final int NORMAL_FRAME_MAX_NUMBER = 9;

    private NormalFrameScore currentFrameScore;
    private NormalFrame next;

    NormalFrame() {
        this.currentFrameScore = new NormalFrameScore();
    }

    @Override
    Frame bowl(int downCount) {
        if (currentFrameScore.addBowlScore(downCount)) {
            return this;
        }
        return next(downCount);
    }

    @Override
    boolean isGameOver() {
        return getIndex() == NORMAL_FRAME_MAX_NUMBER;
    }

    private NormalFrame next(int downCount) {
        next = new NormalFrame();
        next.bowl(downCount);
        return next;
    }
}
