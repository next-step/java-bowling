package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 11:50
 */
public interface State {
    State update(Point fallCount, boolean isFinalFrame);

    boolean isOver(boolean isFinalFrame);

    String printState();

    Point getFirstBowl();

    Point getSecondBowl();

    Score stateScore();

    Score updateScore(Score sourceScore);
}
