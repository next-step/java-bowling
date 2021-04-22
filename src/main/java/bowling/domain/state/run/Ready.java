package bowling.domain.state.run;

import bowling.domain.engine.State;
import bowling.domain.state.finish.Strike;
import org.apache.logging.log4j.util.Strings;

import static bowling.domain.pin.Pin.BOWLING_PIN_MAX_SIZE;

public class Ready implements State {

    @Override
    public State bowl(final int downPins) {
        if (downPins == BOWLING_PIN_MAX_SIZE) {
            return new Strike();
        }
        return new Hit(downPins);
    }

    @Override
    public String printResult() {
        return Strings.EMPTY;
    }

    @Override
    public String printLastResult() {
        return Strings.EMPTY;
    }

}
