package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.BowlingGameException;

public class Ready extends Running {
    private static final int STRIKE = 10;

    @Override
    public State bowl(int countOfPins) {
        if(countOfPins == STRIKE) {
            return new Strike();
        }
        return new FirstBowl(countOfPins);
    }

    @Override
    public Score getScore() {
        throw new BowlingGameException("레디 중인 상태에서 스코어를 조회할 수 없습니다.");
    }

    @Override
    public String expression() {
        return "";
    }

    @Override
    public Score calculateAddScore(Score beforeScore) {
        return null;
    }

}
