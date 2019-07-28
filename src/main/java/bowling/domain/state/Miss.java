package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.IllegalBowlCountException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 13:09
 */
public class Miss implements State {
    public static final String DELIMITER = "|";
    private final State firstBowl;
    private final Point secondBowl;

    public Miss(State state, Point fallCount) {
        this.firstBowl = state;
        this.secondBowl = fallCount;
    }

    @Override
    public State update(Point fallCount, boolean isFinalFrame) {
        throw new IllegalBowlCountException();
    }

    @Override
    public boolean isOver(boolean isFinalFrame) {
        return Boolean.TRUE;
    }

    @Override
    public String printState() {
        return firstBowl.printState() + DELIMITER + printGutter();
    }

    @Override
    public Point getFirstBowl() {
        return firstBowl.getFirstBowl();
    }

    @Override
    public Point getSecondBowl() {
        return secondBowl;
    }

    @Override
    public Score stateScore() {
        return Score.ofMiss(firstBowl.getFirstBowl().fallCount() + secondBowl.fallCount());
    }

    @Override
    public Score updateScore(Score sourceScore, Score targetScore) {
        return null;
    }

    private String printGutter() {
        if (secondBowl.isGutter()) {
            return Gutter.DISPLAY_STATE;
        }
        return String.valueOf(secondBowl.fallCount());
    }

    @Override
    public String toString() {
        return "Miss{" +
                "firstBowl=" + firstBowl +
                ", secondBowl=" + secondBowl +
                '}';
    }
}
