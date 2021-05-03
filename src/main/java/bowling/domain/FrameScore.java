package bowling.domain;

public class FrameScore {
    private Score score;
    private final AddedPitch addedPitch;

    public FrameScore(Score score, AddedPitch addedPitch) {
        this.score = score;
        this.addedPitch = addedPitch;
    }

    public Score score() {
        return score;
    }

    public FrameScore sumScore(int point) {
        score = score.sum(point);
        addedPitch.useOneBonusPitch();

        return this;
    }

    public boolean isExistsAddCount() {
        return addedPitch.addedBonusCount() > 0;
    }

    public boolean isCalculating() {
        return isExistsAddCount() || !addedPitch.isEnd();
    }
}
