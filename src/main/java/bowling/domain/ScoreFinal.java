package bowling.domain;

public class ScoreFinal extends Score {

    public ScoreFinal(Frame frame) {
        super(frame);
    }

    @Override
    protected int getNextScore() {
        if (state == State.FINAL_DOUBLE
                || state == State.FINAL_TURKEY
        ) {
            return getFirstScore();
        }
        return getScore();
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        if (this.state == State.MISS || this.state == State.GUTTER) {
            this.sumScore += getScore();
            return;
        }
        if (this.state == State.SPARE) {
            this.bonusScore = this.getThirdScore();
            this.sumScore += getScore() + bonusScore;
            return;
        }
        this.bonusScore = this.getSecondScore();
        this.bonusScore += this.getThirdScore();
        this.sumScore += getFirstScore() + bonusScore;
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
