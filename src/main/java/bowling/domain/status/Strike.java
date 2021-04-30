package bowling.domain.status;

public class Strike extends Finish {
    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public String display(int fallenPins) {
        return "X";
    }
}
