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
    Frame initNextFrame() {
        return null;
    }

    @Override
    void addFrame(Frames frames) {
        // do nothing
    }

    @Override
    int getFrameNo() {
        return 0;
    }

    @Override
    StateDtos getFrameResult() {
        return null;
    }

    @Override
    public Score getScore() {
        return Score.UN_SCORE;
    }

    @Override
    Score addBonusScore(Score beforeScore) {
        return beforeScore;
    }
}
