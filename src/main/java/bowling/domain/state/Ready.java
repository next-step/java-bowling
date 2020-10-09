package bowling.domain.state;

public class Ready extends Running {
    @Override
    public State pitch(int fallenPins) {
        if (fallenPins == 10) {
            return new Strike();
        }

        return new FirstBowl(fallenPins);
    }

    @Override
    public int getPitchCount() {
        return 0;
    }
}
