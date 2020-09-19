package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

class Miss extends Finished {
    private final Pin firstPins;
    private final Pin secondPins;

    Miss(Pin firstPins, Pin secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public boolean canBowlFinalFrame() { return false;}

    @Override
    public Score getScore() {
        return new Score(firstPins.totalFallenPins(secondPins), 0);
    }

    public Score calculateAdditionalScore(Score score) {
        score = firstPins.sumScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        score = secondPins.sumScore(score);
        return score;
    }

    @Override
    public String record() {
        return firstPins.record(secondPins);
    }


}
