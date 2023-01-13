package bowling.refactoring.domain.state;

import bowling.refactoring.domain.*;

public class Normal extends Finished {

    private final int totalScore;

    public Normal(Score score) {
        this.totalScore = score.firstScore().count() + score.secondScore().count();
    }

    public int totalScore() {
        return this.totalScore;
    }
}
