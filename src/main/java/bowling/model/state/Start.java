package bowling.model.state;

import bowling.model.Pins;
import bowling.model.Score;
import bowling.model.state.finishedState.Strike;

public class Start extends State {
    private static final String SCORE_ERROR = "시작 상태는 스코어를 반환할 수 없습니다.";
    private static final String EXPRESSION = " ";

    @Override
    public State bowling(int fallenPin) {
        Pins firstScore = Pins.from(fallenPin);

        if (firstScore.isMaxScore()) {
            return Strike.from(firstScore);
        }

        return Open.from(firstScore);
    }

    @Override
    public Score score() {
        throw new IllegalArgumentException(SCORE_ERROR);
    }

    @Override
    public String toString() {
        return EXPRESSION;
    }
}
