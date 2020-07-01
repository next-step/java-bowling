package bowling.domain;

public class ScoreStrike extends Score {

    public ScoreStrike(Frame frame) {
        super(frame);
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        this.bonusScore = nextScore.getScore();
        this.sumScore += this.getScore() + nextScore.getScore() + nextScore.getBonusScore();
        nextScore.setSumScore(this.sumScore);
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
