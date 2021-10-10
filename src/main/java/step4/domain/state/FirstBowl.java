package step4.domain.state;

import java.util.Objects;
import step4.domain.Pins;
import step4.domain.Score;

public class FirstBowl implements State {
    private int falledPins;
    private Score score;


    public FirstBowl(int falledPins) {
        this.falledPins = falledPins;
        this.score = new Score(falledPins, 1);
        Pins.validPins(falledPins);
    }

    public State throwBowl(int falledPins) {
        if (this.falledPins + falledPins == 10) {
            return new Spare(this.falledPins, falledPins);
        }
        return new LastBowl(this.falledPins, falledPins);
    }

    public int getScore() {
        return score.getScore();
    }

    public String getSymbol() {
        return falledPins + "|";
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public Score calculateScore(Score beforeScore) {

        Score newScore = beforeScore.throwBowl(falledPins);
        return newScore;

    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstBowl firstBowl = (FirstBowl) o;
        return falledPins == firstBowl.falledPins && Objects.equals(score, firstBowl.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins, score);
    }
}
