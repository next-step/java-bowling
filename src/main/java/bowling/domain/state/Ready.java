package bowling.domain.state;

import bowling.domain.Pins;

public class Ready implements State{

    @Override
    public State nextPitch(int falledPins) {
        Pins pins = Pins.pitching(falledPins);
        if(pins.isStrike()) {
            return new Strike(pins);
        }
        return new FirstPitch(pins);
    }

    @Override
    public String display() {
        throw new IllegalArgumentException("투구 시작을 하지 않았습니다.");
    }
}
