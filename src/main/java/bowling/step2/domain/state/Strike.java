package bowling.step2.domain.state;

import bowling.step2.domain.Score;

import java.util.List;

public class Strike extends Finished {
    @Override
    public List<Score> getScores() {
        return List.of(new Score(COUNT_OF_MAX_PINS));
    }
}
