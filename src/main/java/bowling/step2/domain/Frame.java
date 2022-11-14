package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    private final List<Score> scores;

    public Frame() {
        this.scores = new ArrayList<>();
    }

    public abstract Boolean isFinalFrame();

    public abstract Boolean IsEndOfOneFrame();

    boolean hasBonusCondition() {
        return isContainingStrike() || isSpare();
    }

    boolean isContainingStrike() {
        return this.scores.stream().anyMatch(Score::isStrike);
    }

    public boolean isSpare() {
        if (this.scores.size() > 2) {
            int scoreCountInFrame = this.scores.size() -1;
            return (this.scores.get(scoreCountInFrame-1).score() + this.scores.get(scoreCountInFrame).score()) == 10;
        }
        if(isContainingStrike()){
            return false;
        }
        return this.scores.stream().mapToInt(it -> it.score()).sum() == 10;
    }

    public void add(int score) {
        scores.add(new Score(score));
    }

    public List<Score> Scores() {
        return new ArrayList<>(scores);
    }
}
