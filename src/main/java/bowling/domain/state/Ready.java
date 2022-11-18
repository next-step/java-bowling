package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class Ready extends Running {

    private static final int NO_SCORE = 0;
    private static final int DEFAULT = 2;

    @Override
    public State bowl(final Pin pin) {

        if (pin.isKnockDown()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }

    @Override
    public Score getScore() {

        return new Score(NO_SCORE, DEFAULT);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {

        return score;
    }

    @Override
    public List<Pin> pins() {

        return List.of();
    }

    @Override
    public boolean hasPins(final int size) {

        return pins().size() == size;
    }
}
