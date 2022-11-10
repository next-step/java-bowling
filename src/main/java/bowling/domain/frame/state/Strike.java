package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.frame.Score;

public class Strike extends Finished {
    @Override
    public Score createScore() {
        return new Score(List.of(10));
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }
}
