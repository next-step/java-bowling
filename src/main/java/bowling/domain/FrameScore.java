package bowling.domain;

public class FrameScore {
    private Score score;
    private BonusPitch bonusPitch;

    public FrameScore(Score score, BonusPitch bonusPitch) {
        this.score = score;
        this.bonusPitch = bonusPitch;
    }

    public Score score() {
        return score;
    }

    public boolean isExistsAddCount() {
        return bonusPitch.addedBonusCount() > 0;
    }
}
