package bowling.domain.state;

import java.util.Collections;
import java.util.List;

import static bowling.domain.pin.DownedPins.MAX_NUM_OF_DOWNED_PINS;

public class Strike extends EndState {

    public static Strike instance() {
        return new Strike();
    }

    @Override
    protected boolean isClean() {
        return true;
    }

    @Override
    public List<Integer> getDownedPins() {
        return Collections.singletonList(MAX_NUM_OF_DOWNED_PINS);
    }
}
