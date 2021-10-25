package bowling.model.state;

import bowling.model.Score;

public class Miss extends Finished {
    private final Pin firstPins;
    private final Pin secondPins;

    Miss(Pin firstPins, Pin secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
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

    @Override
    public Score getScore() {
        return new Score(firstPins.totalPins(secondPins), 0);
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(secondPins);
    }

}
