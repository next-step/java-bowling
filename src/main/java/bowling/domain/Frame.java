package bowling.domain;

import bowling.domain.state.State;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 14:50
 */
public abstract class Frame {
    protected State state;
    protected FrameNumber frameNumber;

    public abstract Frame bowl(int fallCount);

    public abstract boolean isGameOver();

    public abstract Score getScore();

    public abstract Score updateScore(Score source);

    public State getState() {
        return state;
    }

    public boolean matchNumber(int frameNumber) {
        return this.frameNumber.matchFrameNumber(frameNumber);
    }
}
