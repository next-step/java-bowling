package step4.domain.state;

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
}
