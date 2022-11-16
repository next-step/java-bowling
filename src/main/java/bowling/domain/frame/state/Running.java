package bowling.domain.frame.state;

import bowling.domain.frame.Score;

abstract class Running extends State {
    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final Score getScore() {
        return Score.needToMoreBowl();
    }

    @Override
    public final boolean isStrike() {
        return false;
    }

    @Override
    public final boolean isSpare() {
        return false;
    }
}
