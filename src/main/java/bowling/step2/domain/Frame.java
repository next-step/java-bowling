package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    private final List<Score> scores;

    public Frame() {
        this.scores = new ArrayList<>();
    }

    public abstract Boolean isFinalFrame();

    public abstract Boolean isEndedOneFrame();

    boolean hasBonusCondition() {
        return isContainingStrike() || isSpare();
    }

    boolean isContainingStrike() {
        return this.scores.stream().anyMatch(Score::isStrike);
    }

    public boolean isSpare() {
        if (this.scores.size() > 2) {
            int lastScoreIndex = this.scores.size() -1;
            return (Integer.parseInt(this.scores.get(lastScoreIndex-1).score()) + Integer.parseInt(this.scores.get(lastScoreIndex).score())) == 10;
        }
        if(isContainingStrike()){
            return false;
        }
        return this.scores.stream().mapToInt(it -> Integer.parseInt(it.score())).sum() == 10;
    }

    public void add(String score) {
        scores.add(new Score(score));
    }

    public List<Score> Scores() {
        return new ArrayList<>(scores);
    }
}
