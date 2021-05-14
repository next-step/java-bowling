package bowling.entity.score;

import bowling.entity.Score;

public abstract class OnGoing implements ScoreType {
    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public Score frameScore() {
        throw new CalculateImPossibleException();
    };
}
