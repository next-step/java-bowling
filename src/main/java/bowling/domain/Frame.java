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
interface Frame {
    Frame bowl(int fallCount);

    boolean isGameOver();

    Score getScore();

    Score updateScore(Score source);

    State getState();

    boolean matchNumber(int frameNumber);
}
