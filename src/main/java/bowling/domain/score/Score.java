package bowling.domain.score;

import bowling.domain.roll.Roll;

public class Score {

    private int score = 0;

    public void add(Roll roll) {
        this.score += roll.getCountOfKnockedPins();
    }

    public void add(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
