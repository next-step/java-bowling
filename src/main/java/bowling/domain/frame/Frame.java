package bowling.domain.frame;

import bowling.domain.dto.StateDtos;
import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;

public abstract class Frame {

    public abstract void bowl(PinCount countOfPin);

    public abstract Frame initNextFrame();

    public abstract void addFrame(Frames frames);

    public boolean isGameOver() {
        return false;
    }

    public abstract boolean isTurnOver();

    public abstract int getFrameNo();

    public abstract StateDtos getFrameResult();

    public abstract Score getScore();

    public abstract Score addBonusScore(Score beforeScore);
}
