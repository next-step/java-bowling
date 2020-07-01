package bowling.domain;

public class ScoreSpare extends Score {
    public ScoreSpare(Pins pins) {
        super(pins);
    }

    @Override
    public void addBonusNumber(Score nextScore) {
        this.bonusScore = nextScore.firstScore;
        this.sumScore += score + bonusScore;
        nextScore.sumScore = this.sumScore;
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
