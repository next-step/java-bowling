package bowling.domain.status;

public class Spare implements Status {
    @Override
    public String display(int fallenPins) {
        return "/";
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
