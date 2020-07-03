package bowling.domain;

public class ScoreNormal extends Score {

    public ScoreNormal(Frame frame) {
        super(frame);
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        this.sumScore += this.getScore();
        nextScore.setSumScore(this.sumScore);
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
