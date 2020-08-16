package bowling.domian.state;

import bowling.domian.frame.Score;

public class Ready implements State {
    public State bowl(int falledPinsCount) {
        Pins pins = Pins.bowl(falledPinsCount);

        if (pins.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(pins);
    }

    public boolean isFinished() {
        return false;
    }

    public boolean canGetScore() {
        return true;
    }

    public Score getScore() {
        return null;
    }
}
