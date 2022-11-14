package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class Strike extends State {

    private static final int MAX_COUNT = 10;

    @Override
    public State bowl(Pin pin) {

        throw new IllegalArgumentException("더 이상 공을 던질 수 없습니다.");
    }

    @Override
    public boolean isFinished() {

        return true;
    }

    @Override
    public Score getScore() {

        return new Score(MAX_COUNT, 2);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {

        return score.add(MAX_COUNT);
    }

    @Override
    public List<Pin> pins() {

        return List.of(new Pin(MAX_COUNT));
    }
}
