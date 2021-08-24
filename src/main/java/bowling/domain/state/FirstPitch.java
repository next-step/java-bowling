package bowling.domain.state;

import bowling.domain.Pins;

public class FirstPitch implements State{
    private Pins firstPins;

    public FirstPitch(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State nextPitch(int pins) {
        Pins secondPins = Pins.pitching(pins);
        if(firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }
        return new Miss(firstPins, secondPins);
    }

    @Override
    public String display() {
        return firstPins.display();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
