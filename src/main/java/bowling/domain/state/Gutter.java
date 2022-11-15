package bowling.domain.state;

import bowling.domain.frame.Frame;

import java.util.List;

public class Gutter implements FrameState {

    protected Gutter() {
    }

    @Override
    public boolean identify(List<Integer> hits) {
        return hits.get(hits.size() - 1) == Frame.MIN_NUMBER_OF_BOWLING_PINS;
    }

}
