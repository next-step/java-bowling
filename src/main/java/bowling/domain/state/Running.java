package bowling.domain.state;

public abstract class Running implements State {
    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public boolean canAccumulate() {
        return false;
    }
}
