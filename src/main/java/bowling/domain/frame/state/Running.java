package bowling.domain.frame.state;

import bowling.domain.frame.Score;

abstract class Running implements State {
    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final Score getScore() {
        return Score.needToMoreBowl();
    }
}
