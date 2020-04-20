package bowling.domain.state;

import bowling.domain.Score;

public class Miss extends Finished {
    private Score firstScore;
    private Score secondScore;

    public Miss(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public Miss(Score firstScore) {
        this.firstScore = firstScore;
    }

    @Override
    public String display() {
        return super.display();
    }
}
