package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Strike extends State {

    @Override
    public State bowl(Pin pin) {
        throw new UnsupportedOperationException("더 이상 공을 던질 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Score getScore() {
        return new Score(10, 2);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score.add(10);
    }

    public String describe() {
        return "X";
    }
}
