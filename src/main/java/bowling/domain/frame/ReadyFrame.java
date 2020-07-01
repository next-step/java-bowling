package bowling.domain.frame;

import bowling.domain.dto.StateDtos;
import bowling.domain.pin.PinCount;
import bowling.domain.score.Score;

public class ReadyFrame extends Frame {

    private ReadyFrame() {
    }

    public static Frame newInstance() {
        return new ReadyFrame();
    }

    @Override
    public void bowl(PinCount countOfPin) {
        // do nothing
    }

    @Override
    public Frame initNextFrame() {
        return newInstance();
    }

    @Override
    public void addFrame(Frames frames) {
        // do nothing
    }

    @Override
    public boolean isTurnOver() {
        return false;
    }

    @Override
    public int getFrameNo() {
        return 0;
    }

    @Override
    public StateDtos getFrameResult() {
        return null;
    }

    @Override
    public Score getScore() {
        return Score.UN_SCORE;
    }

    @Override
    public Score addBonusScore(Score beforeScore) {
        return beforeScore;
    }
}
