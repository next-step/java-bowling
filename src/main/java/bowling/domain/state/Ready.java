package bowling.domain.state;

import org.apache.logging.log4j.util.Strings;

public class Ready extends Running {
    @Override
    public State pitch(int fallenPins) {
        if (fallenPins == 10) {
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
