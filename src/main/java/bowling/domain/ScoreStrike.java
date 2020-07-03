package bowling.domain;

public class ScoreStrike extends Score {

    private static final int DOUBLE_STRIKE_ADDING_POINT = 10;

    public ScoreStrike(Frame frame) {
        super(frame);
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        this.bonusScore = nextScore.getNextScore();
        if (nextScore.isFirstStrike()) {
            this.bonusScore += DOUBLE_STRIKE_ADDING_POINT;
        }
        this.sumScore += this.getScore() + this.bonusScore;
        nextScore.setSumScore(this.sumScore);
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
