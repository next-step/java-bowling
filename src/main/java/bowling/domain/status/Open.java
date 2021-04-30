package bowling.domain.status;

public class Open implements Status {
    @Override
    public String display(int fallenPins) {
        return String.valueOf(fallenPins);
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
        return true;
    }
}
