package seul.bowling.domain.status;

import lombok.Getter;
import seul.bowling.domain.Pins;

@Getter
public class Ready extends Running {
    private static final int CLEAR_PIN_NUMBER = 10;

    @Override
    public Status addPins(int clearPin) {
        if (clearPin == CLEAR_PIN_NUMBER) {
            return new Strike(clearPin);
        }

        return new Hold(clearPin);
    }

    @Override
    public Pins getPins() {
        return new Pins();
    }

    @Override
    public boolean equalsStatus(Status status) {
        return this.getClass().equals(status.getClass());
    }
}
