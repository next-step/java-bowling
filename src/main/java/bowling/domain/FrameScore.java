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
        if (!isExistsAddCount()) {
            return this;
        }
        return new FrameScore(score.sum(point), bonusPitch.useOneBonusPitch());
    }

    public boolean isExistsAddCount() {
        return bonusPitch.addedBonusCount() > 0;
    }
}
