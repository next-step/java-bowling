package bowling.domain.frame.state;

public class Strike implements State {
    @Override
    public State bowl(int pins) {
        throw new UnsupportedOperationException();
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
