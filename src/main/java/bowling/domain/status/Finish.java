package bowling.domain.status;

public abstract class Finish implements Status {
    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
