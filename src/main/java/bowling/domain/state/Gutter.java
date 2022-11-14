package bowling.domain.state;

import bowling.domain.frame.BowlingGameFrame;

import java.util.List;

public class Gutter implements BowlingGameHitState {

    protected Gutter() {
    }

    @Override
    public boolean identify(List<Integer> hits) {
        return hits.get(hits.size() - 1) == BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PINS;
    }

}
