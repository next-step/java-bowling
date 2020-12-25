package bowling_step3.domain.state;

import org.apache.logging.log4j.util.Strings;
import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;

public class Ready extends Running {

    @Override
    public State pitch(int fallenPins) {
        if (fallenPins == BOWLING_MAX_NUMBER) {
            return new Strike();
        }
        return new FirstBowl(fallenPins);
    }

    @Override
    public int getPitchCount() {
        return 0;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public String toString() {
        return Strings.EMPTY;
    }
}
