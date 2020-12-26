package step2.domain.state;

import step2.domain.Score;

public class Strike extends Finished {

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }
}
