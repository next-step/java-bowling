package bowling.domain.status;

public class Strike implements Status {
    @Override
    public Status roll(int fallenPins) {
        return new Final();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean hasBonusPitch() {
        return true;
    }

    @Override
    public String display(int fallenPins) {
        return "X";
    }
}
