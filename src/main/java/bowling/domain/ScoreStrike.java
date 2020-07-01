package bowling.domain;

public class ScoreStrike extends Score {

    public ScoreStrike(Pins pins) {
        super(pins);
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        this.bonusScore = nextScore.score;
        this.sumScore += score + nextScore.score + nextScore.bonusScore;
        nextScore.sumScore = this.sumScore;
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
