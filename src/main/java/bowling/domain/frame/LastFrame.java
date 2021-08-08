package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.state.pitching.LastPitching;
import bowling.domain.state.CommonState;

import java.util.List;

public class LastFrame implements Frame {

    private CommonState state;

    private LastFrame(CommonState state) {
        this.state = state;
    }

    public static LastFrame of() {
        return new LastFrame(LastPitching.of());
    }

    @Override
    public boolean isBowlingFinish() {
        return false;
    }

    @Override
    public void hitPins(Pins pins) {
        this.state = state.hitPins(pins);
    }

    @Override
    public void addFrame(List<Frame> frames) {

    }
}
