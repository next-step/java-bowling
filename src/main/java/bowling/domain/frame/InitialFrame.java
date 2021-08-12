package bowling.domain.frame;

import bowling.domain.dto.StateData;
import bowling.domain.score.Score;
import bowling.domain.state.CommonState;
import bowling.domain.state.pitching.FirstPitching;

public class InitialFrame implements Frame {

    private final CommonState state;

    private InitialFrame(CommonState state) {
        this.state = state;
    }

    public static InitialFrame of() {
        return new InitialFrame(FirstPitching.of());
    }

    @Override
    public boolean isBowlingFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.NULL;
    }

    @Override
    public Score addBonusScore(Score score) {
        return score;
    }

    @Override
    public StateData getFrameStates() {
        return null;
    }
}
