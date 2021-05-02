package bowling.domain.status;

public class Strike implements Status {
    @Override
    public Status roll(int fallenPins) {
        return new Final();
    }

    @Override
    public boolean isStrike() {
        return true;
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
    public String display(int fallenPins) {
        return "X";
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean hasBonusPitch() {
        return true;
    }
}
