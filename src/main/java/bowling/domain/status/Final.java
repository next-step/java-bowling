package bowling.domain.status;

public class Final implements Status {
    @Override
    public Status roll(int fallenPins) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public String display(int fallenPins) {
        return null;
    }

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

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }
}
