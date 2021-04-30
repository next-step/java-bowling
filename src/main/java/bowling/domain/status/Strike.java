package bowling.domain.status;

public class Strike implements Status {
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
}
