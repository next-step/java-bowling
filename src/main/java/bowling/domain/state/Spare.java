package bowling.domain.state;

import bowling.domain.Score;

public class Spare extends Finished {
    private Score firstScore;
    private Score secondScore;

    public Spare(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    @Override
    public String display() {
        return super.display();
    }
}
