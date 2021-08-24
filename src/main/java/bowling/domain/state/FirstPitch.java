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
        throw new IllegalArgumentException("아직 1번의 투구가 남아있습니다.");
    }
}
