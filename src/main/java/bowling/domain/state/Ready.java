package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NotPitchingException;

import java.util.List;

public class Ready implements State {

    @Override
    public State nextPitch(int falledPins) {
        Pins pins = Pins.pitching(falledPins);
        if(pins.isStrike()) {
            return new Strike(pins);
        }
        return new FirstPitch(pins);
    }

    @Override
    public State lastPitch(int falledPins) {
        Pins pins = Pins.pitching(falledPins);
        if(pins.isStrike()) {
            return new Strike(pins);
        }
        return new Finish(pins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<State> lastSpare(List<State> list, State state) {
        throw new NotPitchingException("투구 시작을 하지 않았습니다.");
    }

    @Override
    public int firstPins() {
        return 0;
    }

    @Override
    public int secondPins() {
        return 0;
    }
}
