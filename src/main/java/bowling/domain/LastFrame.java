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
}
