package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public class Miss extends Finish {

    private final Pin first;
    private final Pin second;

    public Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Score createScore() {
        return null;
    }

}
