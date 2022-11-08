package bowling.domain.frame.state;

import java.util.List;

public class Strike implements State {
    @Override
    public State bowl(int pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score createScore() {
        return new Score(List.of(10));
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }
}
