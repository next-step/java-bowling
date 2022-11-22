package bowling.domain.state;

import bowling.domain.Score2;

public class Strike extends Finished {

    @Override
    public Score2 getScore() {
        return new Score2(10, 2);
    }
}
