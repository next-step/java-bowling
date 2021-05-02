package bowling.domain.status;

public class Spare implements Status {
    @Override
    public Status roll(int fallenPins) {
        return null;
    }

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

    @Override
    public boolean isEnd() {
        return true;
    }
}
