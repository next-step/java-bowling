package domain.state;

import domain.Pins;
import domain.score.BonusType;
import domain.score.Score;

import static io.OutputResult.SYMBOL_DELIMITER;

public class Open implements State {
    private final Pins first;
    private final Pins second;

    public Open(Pins first, Pins second) {
        verify(first, second);
        this.first = first;
        this.second = second;
    }

    private void verify(Pins first, Pins second) {
        if (isClear(first, second)) {
            throw new IllegalArgumentException("상태와 맞지 않는 정보 입니다.");
        }
    }

    private boolean isClear(Pins first, Pins second) {
        return Pins.ALL.equals(first.add(second));
    }

    @Override
    public boolean isClosed() {
        return true;
    }


    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.calculate(getFirstScore());
        if (score.hasBonus()) {
            return score.calculate(getSecondScore());
        }
        return score;
    }

    @Override
    public Score getScore() {
        return Score.of(first, second, BonusType.normal());
    }

    @Override
    public String toSymbol() {
        return first + SYMBOL_DELIMITER + second;
    }

    private Score getFirstScore() {
        return Score.of(first, Pins.ZERO, BonusType.spare());
    }

    private Score getSecondScore() {
        return Score.of(Pins.ZERO, second, BonusType.spare());
    }
}
