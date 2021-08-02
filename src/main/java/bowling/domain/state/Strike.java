package bowling.domain.state;

import bowling.domain.score.CalculableScore;
import bowling.domain.score.InProgressScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

import static bowling.domain.pin.DownedPins.MAX_NUM_OF_DOWNED_PINS;

public class Strike extends EndState {

    public static Strike init() {
        return new Strike();
    }

    @Override
    public Score Score() {
        return InProgressScore.ofStrike();
    }

    @Override
    protected boolean isClean() {
        return true;
    }

    @Override
    public List<Integer> getDownedPins() {
        return Collections.singletonList(MAX_NUM_OF_DOWNED_PINS);
    }
}
