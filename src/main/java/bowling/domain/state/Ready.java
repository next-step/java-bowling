package bowling.domain.state;

public class Ready extends Running {
    @Override
    public State bowl(int pin) {
        if (pin == MAX_PIN_NO) {
            return new Strike();
        }
        return new FirstBowl(pin);
    }
}
