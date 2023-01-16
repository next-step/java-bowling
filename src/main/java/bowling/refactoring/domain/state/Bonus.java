package bowling.refactoring.domain.state;

import bowling.refactoring.domain.*;

public class Bonus extends Finished {

    private final int totalScore;

    public Bonus(Score score) {
        this.totalScore = score.firstScore().count() + score.secondScore().count() + score.bonusScore().count();
    }

    public int totalScore() {
        return this.totalScore;
    }
}
