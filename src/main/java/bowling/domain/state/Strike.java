package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {
    private Score firstScore;

    public Strike() {
        this.firstScore = new Score(10);
    }

    @Override
    public String display() {
        return super.display();
    }
}
