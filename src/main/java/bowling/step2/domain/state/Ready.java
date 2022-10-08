package bowling.step2.domain.state;

public class Ready implements State {
    @Override
    public State bowl(final int fallenPins) {
        if (fallenPins == 0) {
            return new Strike();
        }
        
        return new Normal(fallenPins);
    }
}
