package bowling.domain.state;

public class Ready implements State {
    @Override
    public State pitch(int fallenPins) {
        if (fallenPins == 10) {
            return new Strike();
        }

        return new FirstBowl(fallenPins);
    }
}
