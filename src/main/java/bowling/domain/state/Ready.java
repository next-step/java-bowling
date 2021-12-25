package bowling.domain.state;

public class Ready implements State {
    private static final int STRIKE_COUNT = 10;

    @Override
    public State bowl(int pinCount) {
        if (pinCount == STRIKE_COUNT) {
            return new Strike();
        }

        return new FirstBowl(pinCount);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }
}
