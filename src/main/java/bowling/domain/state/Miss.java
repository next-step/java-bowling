package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.IllegalCalculateException;

public class Miss extends Finished {
    private int preBowl;

    public Miss(int firstBowl, int secondBowl) {
        super(secondBowl, String.valueOf(secondBowl));
        this.preBowl = firstBowl;
    }

    @Override
    public Score getScore() {
        return new Score(fallenPins + preBowl, NO_MORE_CALCULATION);
    }

    @Override
    public Score calculateAdditionalScore(Score score, int fallenPins) {
        throw new IllegalCalculateException("실행할 수 없는 메서드 입니다.");
    }
}
