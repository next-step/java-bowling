package bowling.domain;

public class ScoreNormal extends Score {

    public ScoreNormal(Pins pins) {
        super(pins);
    }

    @Override
    public void addBonusNumber(Score nextScore) {

        this.bonusScore = 0;
        this.sumScore += score + bonusScore;
        nextScore.sumScore = this.sumScore;
    }

    @Override
    public void reset() {
        this.sumScore = 0;
    }

}
