package step4.domain.state;

import step4.domain.Score;

public class FirstBowl implements State {
    private int falledPins;
    private Score score;

    public FirstBowl(int falledPins) {
        this.falledPins = falledPins;
        this.score = new Score(falledPins, 1);
    }

    public State throwBowl(int falledPins) {
        if (this.falledPins + falledPins == 10) {
            return new Spare(this.falledPins, falledPins);
        }
        return new LastBowl(this.falledPins, falledPins);
    }

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
        beforeScore.throwBowl(falledPins);
        return beforeScore;
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
