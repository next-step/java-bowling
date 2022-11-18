package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class Strike extends Finished {

    private static final int MAX_SCORE = 10;
    private static final int LEFT_CHANCE = 2;

    @Override
    public State bowl(final Pin pin) {

        throw new IllegalArgumentException("더 이상 공을 던질 수 없습니다.");
    }

    @Override
    public Score getScore() {

        return new Score(MAX_SCORE, LEFT_CHANCE);
    }

    @Override
    public Score calculateAdditionalScore(final Score score) {

        return score.add(MAX_SCORE);
    }

    @Override
    public List<Pin> pins() {

        return List.of(new Pin(MAX_SCORE));
    }

    @Override
    public boolean hasPins(final int size) {

        return pins().size() == size;
    }
}
