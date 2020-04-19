package bowling.domain.State;

import bowling.domain.Score;

public class Spare extends Finished {
    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException("첫번쨰 핀과 두번째 쓰러트린 핀의 합이 10이 아닙니다.");
        }

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(secondPins);
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
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
