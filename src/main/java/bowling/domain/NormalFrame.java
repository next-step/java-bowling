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

    private NormalFrameScore currentFrameScore;
    private NormalFrame next;

    public NormalFrame() {
        this.currentFrameScore = new NormalFrameScore();
    }

    @Override
    NormalFrame bowl(int downCount) {
        if (currentFrameScore.addBowlScore(downCount)) {
            return this;
        }
        return next();
    }

    private NormalFrame next() {
        next = new NormalFrame();
        return next;
    }
}


