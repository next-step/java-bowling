package bowling.domain;

public class ScoreNormal extends Score {

    public ScoreNormal(Frame frame) {
        super(frame);
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        this.bonusScore = 0;
        this.sumScore += getScore()+ bonusScore;
        nextScore.setSumScore(this.sumScore);
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
