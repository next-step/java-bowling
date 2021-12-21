package bowling.domain.bowl;

import bowling.domain.score.Score;

public abstract class ProceedingBowl implements Bowl {

    @Override
    public boolean canPitch() {
        return true;
    }

    @Override
    public Score score() {
        return Score.base();
    }
}
