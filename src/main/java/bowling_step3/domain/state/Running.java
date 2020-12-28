package bowling_step3.domain.state;

import bowling_step3.domain.Score;
import bowling_step3.exception.FrameFinishedScoreCreateException;

public abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new FrameFinishedScoreCreateException();
    }
}
