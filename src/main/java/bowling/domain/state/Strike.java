package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.ScoreV2;

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
    public ScoreV2 getScore() {
        return new ScoreV2(10, 2);
    }

    @Override
    public ScoreV2 calculateAdditionalScore(ScoreV2 scoreV2) {
        return scoreV2.add(10);
    }

    public String describe() {
        return "X";
    }
}
