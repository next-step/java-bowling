package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

public class NormalFrame extends Frame {

    private static final String NORMAL_FRAME_RANGE = "NormalFrame의 범위는 1~9 사이입니다.";

    public NormalFrame(int frameIndex) {
        super(frameIndex);
    }

    @Override
    protected void validationFrameIndex(int frameIndex) {
        if (frameIndex < MIN_FRAME_INDEX || frameIndex > MAX_FRAME_INDEX - 1) {
            throw new IllegalArgumentException(NORMAL_FRAME_RANGE);
        }
    }

    @Override
    public boolean rollingEnd() {
        return state.isFinish();
    }

    @Override
    public void bowl(Pin pin) {
        state = state.bowl(pin);
    }

    @Override
    public boolean isEndAllFrame() {
        return false;
    }

    @Override
    public String index() {
        return String.valueOf(frameIndex);
    }

    @Override
    public String currentFrameStatus() {
        return state.record();
    }


    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);

        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    @Override
    public int score() {
        if (!rollingEnd()) {
            return -1;
        }

        try {
            Score score = state.getScore();
            if (score.canCalculateScore()) {
                return score.getScore();
            }
            return next.calculateAdditionalScore(score).getScore();
        } catch (CannotCalculateException e) {
            return -1;
        }
    }

}
