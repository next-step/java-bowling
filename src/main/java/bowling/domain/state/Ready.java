package bowling.domain.state;

import bowling.domain.PitchResults;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;

public class Ready extends Running{

    public Ready(){
        this.pitchResults = PitchResults.from(new ArrayList<>());
    }

    @Override
    public State pitch(int firstKnockedDownPins) {

        addPitchResult(firstKnockedDownPins);

        if (this.pitchResults.isStrike()) {
            return new Strike(this.pitchResults);
        }

        return new FirstBowl(this.pitchResults);
    }

    @Override
    public int getPitchTryCount() {
        return MIN_PITCH_COUNT;
    }

    @Override
    public String toString() {
        return Strings.EMPTY;
    }
}
