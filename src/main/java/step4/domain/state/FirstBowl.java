package step4.domain.state;

import step4.domain.Score;
import step4.exception.NeedAdditionalFrameException;

public class FirstBowl implements State {
    private int falledPins;
    private Score score;
    private String symbol;

    public FirstBowl(int falledPins) {
        this.falledPins = falledPins;
        this.score = new Score(falledPins, 1);
        this.symbol = falledPins + "|";
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

    public String getSymbol() {
        return symbol;
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
