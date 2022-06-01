package bowling.domain;

import bowling.exception.CannotCalculateScoreException;
import java.util.OptionalInt;

public class NormalFrame extends Frame {

    public NormalFrame() { }

    public NormalFrame(Frame beforeFrame, Frame nextFrame) {
        super(beforeFrame, nextFrame);
    }

    @Override
    public OptionalInt scoreCalculated() {
        if (!state.canCalculateScore()) {
            return OptionalInt.empty();
        }

        Score score = state.score();

        if (score.isAddedAllBonus()) {
            return score.getAsOptionalInt();
        }

        try {
            score = nextFrame.bonusScore(score);
        } catch (CannotCalculateScoreException e) {
            return OptionalInt.empty();
        }

        return score.getAsOptionalInt();
    }

}
