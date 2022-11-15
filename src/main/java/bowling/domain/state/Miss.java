package bowling.domain.state;

import bowling.domain.frame.Frame;

import java.util.List;

public class Miss implements FrameState {

    protected Miss() {
    }

    @Override
    public boolean identify(List<Integer> hits) {
        if (hits.size() < 2) {
            return false;
        }

        int indexOfHit = hits.size() - 1;
        return hits.get(indexOfHit - 1) + hits.get(indexOfHit) < Frame.MAX_NUMBER_OF_BOWLING_PINS;
    }

}
