package bowling.domain.status;

public class Final implements Status {
    @Override
    public Status roll(int fallenPins) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean hasBonusPitch() {
        return false;
    }

    @Override
    public String display(int fallenPins) {
        return null;
    }
}
