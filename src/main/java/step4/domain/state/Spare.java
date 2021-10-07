package step4.domain.state;

import java.util.Objects;
import step4.domain.Score;

public class Spare implements State {
    private int firstFalledPins;
    private int secondFalledPins;
    private Score score;
    private String symbol;

    public Spare(int firstFalledPins, int secondFalledPins) {
        this.firstFalledPins = firstFalledPins;
        this.secondFalledPins = secondFalledPins;
        this.score = new Score(10, 1);
        this.symbol = this.firstFalledPins + "|" + "-";
    }

    public State throwBowl(int falledPins) {
        return null;
    }

    @Override
    public String getScore() {
        return score.getScore();
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }
        beforeScore.throwBowl(firstFalledPins);
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }
        beforeScore.throwBowl(secondFalledPins);
        return beforeScore;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spare spare = (Spare) o;
        return firstFalledPins == spare.firstFalledPins
            && secondFalledPins == spare.secondFalledPins
            && Objects.equals(score, spare.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFalledPins, secondFalledPins, score);
    }
}
