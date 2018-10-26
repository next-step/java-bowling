package bowling.frame.state;

import bowling.frame.Score;

class Miss extends Finished {
    private final Pins firstPins;
    private final Pins secondPins;

    Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score getScore() {
        return new Score(firstPins.totalPins(secondPins), 0);
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
    public String getDesc() {
        return firstPins.getDesc(secondPins);
    }
}
