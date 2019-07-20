package domain.state;

import domain.Pins;
import domain.score.BonusType;
import domain.score.Score;

public class Spares implements State {

    private final static String SPARES = "/";
    private final Pins first;
    private final Pins second;

    public Spares(Pins first, Pins second) {
        verify(first, second);
        this.first = first;
        this.second = second;
    }

    private void verify(Pins first, Pins second) {
        if (isNotSpares(first, second)) {
            throw new IllegalArgumentException("핀이 남아 있는데");
        }
    }

    private boolean isNotSpares(Pins first, Pins second) {
        return !Pins.ALL.equals(first.add(second));
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.of(first, second, BonusType.spare());
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.calculate(getFirstScore());
        if(score.hasBonus()) {
            return score.calculate(getSecondScore());
        }
        return score;
    }

    public Score getFirstScore() {
        return Score.of(first, Pins.ZERO, BonusType.spare());
    }

    public Score getSecondScore() {
        return Score.of(Pins.ZERO, second, BonusType.spare());
    }

    @Override
    public String toSymbol() {
        return first + "|" + SPARES;
    }
}
