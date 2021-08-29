package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;
import bowling.exception.NotPitchingException;

import java.util.List;

public class FirstPitch implements State {
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
    public State lastPitch(int pins) {
        throw new NextPitchingException("10 프레임");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<State> lastSpare(List<State> list, State state) {
        throw new NotPitchingException("10 프레임이 아닙니다.");
    }

    @Override
    public int firstPins() {
        return firstPins.getFalledPins();
    }

    @Override
    public int secondPins() {
        return 0;
    }
}
