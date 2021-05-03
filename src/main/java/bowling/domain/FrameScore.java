package bowling.domain;

public class FrameScore {
    private Score score;
    private final BonusPitch bonusPitch;

    public FrameScore(Score score, BonusPitch bonusPitch) {
        this.score = score;
        this.bonusPitch = bonusPitch;
    }

    public Score score() {
        return score;
    }

    public FrameScore sumScore(int point) {
        score = score.sum(point);
        bonusPitch.useOneBonusPitch();

        return this;
    }

    public boolean isExistsAddCount() {
        return bonusPitch.addedBonusCount() > 0;
    }

    public boolean isCalculating() {
        return isExistsAddCount() || !bonusPitch.isEnd();
    }
}
