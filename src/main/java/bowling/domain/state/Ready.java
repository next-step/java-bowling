package bowling.domain.state;

import bowling.domain.Pins;
import org.apache.logging.log4j.util.Strings;

public class Ready extends BaseState {

    public Ready() {
        super(0);
    }

    @Override
    public FrameState bowl(int downPins) {
        Pins pitch = Pins.valueOf(downPins, 0);
        if (pitch.isStrike()) {
            return new Strike();
        }
        return new Hit(downPins);
    }

    @Override
    public String printResult() {
        return Strings.EMPTY;
    }

}
