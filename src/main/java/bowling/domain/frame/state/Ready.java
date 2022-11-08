package bowling.domain.frame.state;

import java.util.List;

public class Ready implements State {
    @Override
    public State bowl(int falledPins) {
        Pins pins = new Pins(falledPins);
        if (pins.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(pins);
    }

    @Override
    public Score createScore() {
        return new Score(List.of());
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean canBonusBowl() {
        return false;
    }
}
