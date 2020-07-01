package bowling.domain;

public class ScoreSpare extends Score {
    public ScoreSpare(Frame frame) {
        super(frame);
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        this.bonusScore = nextScore.getFirstScore();
        this.sumScore += getScore() + bonusScore;
        nextScore.setSumScore(this.sumScore);
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
