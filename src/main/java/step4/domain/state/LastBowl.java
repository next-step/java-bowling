package step4.domain.state;

import java.util.Objects;
import step4.domain.Score;

public class LastBowl implements State {
    private int firstFalledPins;
    private int lastFalledPins;
    private String symbol;
    private Score score;

    public LastBowl(int firstFalledPins, int lastFalledPins) {
        this.firstFalledPins = firstFalledPins;
        this.lastFalledPins = lastFalledPins;
        this.symbol = firstFalledPins + "|" + lastFalledPins;
        this.score = new Score(firstFalledPins + lastFalledPins, 0);
    }

    @Override
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

    public String getSymbol() {
        return symbol;
    }

    @Override
    public Score calculateScore(Score beforeScore) {

        if (beforeScore.canCalculate()) {
            return  beforeScore;
        }

        beforeScore = beforeScore.throwBowl(firstFalledPins);
        if (beforeScore.canCalculate()) {
            return  beforeScore;
        }

        beforeScore = beforeScore.throwBowl(lastFalledPins);
        return beforeScore;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LastBowl lastBowl = (LastBowl) o;
        return firstFalledPins == lastBowl.firstFalledPins
            && lastFalledPins == lastBowl.lastFalledPins
            && Objects.equals(score, lastBowl.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFalledPins, lastFalledPins, score);
    }
}
