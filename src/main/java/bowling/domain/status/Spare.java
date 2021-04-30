package bowling.domain.status;

public class Spare extends Finish {
    @Override
    public String display(int fallenPins) {
        return "/";
    }

    @Override
    public boolean isSpare() {
        return true;
    }
}
