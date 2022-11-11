package bowling.domain;

public class LastFrame extends Frame {

    @Override
    public boolean isRemainChance() {
        if (this.scores.size() < 2) {
            return true;
        }
        if (this.scores.size() == 2) {
            return isRemainThirdTimeChance();
        }
        return false;
    }

    private boolean isRemainThirdTimeChance() {
        if (this.scores.first().isStrike() || this.scores.second().isStrike()) {
            return true;
        }
        if (Scores.sumScores(this.scores.first(), this.scores.second()) == SCORE_STRIKE) {
            return true;
        }
        return false;
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.scores.size() > 4) {
            throw new IllegalArgumentException();
        }
        if (this.scores.size() >= 2) {
            validateLastFrameSecondTimeScore(this.scores);
        }
        if (this.scores.size() >= 3) {
            validateLastFrameThirdTimeScore(this.scores);
        }
    }

    private void validateLastFrameSecondTimeScore(Scores scores) {
        if (!scores.first().isStrike() && scores.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateLastFrameThirdTimeScore(Scores scores) {
        if (!scores.second().isStrike() && Scores.sumScores(scores.second(), scores.third()) > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }
}
