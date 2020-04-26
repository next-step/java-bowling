package bowling.domain.State;

import bowling.domain.Score;

public class Miss extends Finished {
    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(secondPins);
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(firstPins.totalPins(secondPins));
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = firstPins.sumScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        score = secondPins.sumScore(score);
        return score;
    }
}
