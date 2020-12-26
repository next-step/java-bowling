package bowling_step3.domain.state;

import org.apache.logging.log4j.util.Strings;
import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;

public class Ready extends Running {

    private static final int MIN_COUNT_ZERO = 0;

    @Override
    public State pitch(int countOfKnockDown) {
        if (countOfKnockDown == BOWLING_MAX_NUMBER) {
            return new Strike();
        }
        return new FirstBowl(countOfKnockDown);
    }

    @Override
    public int getPitchCount() {
        return MIN_COUNT_ZERO;
    }

    @Override
    public int getTotalCount() {
        return MIN_COUNT_ZERO;
    }

    @Override
    public String toString() {
        return Strings.EMPTY;
    }
}
