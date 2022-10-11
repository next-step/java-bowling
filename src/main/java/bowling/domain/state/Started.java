package bowling.domain.state;

import bowling.domain.Score;

import java.util.LinkedList;

public abstract class Started implements State{

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State bowl(Score score) {
        if(score.isStrike()){
            return new Strike(score));
        }
        return new Running(score);
    }

    @Override
    public int bonusCount() {
        return 0;
    }
}
