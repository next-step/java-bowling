package bowling.domain.bowl;

import bowling.domain.exception.CannotCalculateException;
import bowling.domain.score.Score;

public abstract class Running implements Bowl{
    @Override
    public boolean isEnd(){
        return false;
    }

    @Override
    public Score score(){
        throw new CannotCalculateException();
    }
}
