package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.CommonState;
import bowling.domain.state.pitching.FirstPitching;

public class InitialFrame extends Frame {

    private InitialFrame(CommonState state) {
        super(state);
    }

    public static InitialFrame of() {
        return new InitialFrame(FirstPitching.of());
    }

    @Override
    public Score getScore() {
        return Score.NULL;
    }

    @Override
    protected Score addBonusScore(Score score) {
        return score;
    }

}
