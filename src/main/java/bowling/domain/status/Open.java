package bowling.domain.status;

public class Open implements Status {
    @Override
    public Status roll(int fallenPins) {
        return null;
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
        return String.valueOf(fallenPins);
    }
}
