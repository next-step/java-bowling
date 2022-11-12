package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<Score> scores;

    public Frame(String score) {
        List<Score> scores = new ArrayList<>();
        scores.add(new Score(score));
        this.scores = scores;
    }

    public boolean hasNext(int index) {
        return index == BowlingGame.GAME_LAST_INDEX || !isContainingStrike();
    }

    public boolean hasBonus(int index) {
        return index == BowlingGame.GAME_LAST_INDEX && (isContainingStrike() || isSpare());
    }

    private boolean isContainingStrike() {
        return this.scores.stream().anyMatch(Score::isStrike);
    }

    public boolean isSpare() {
        if (this.scores.size() > 2) {
            return (Integer.parseInt(this.scores.get(1).score()) + Integer.parseInt(this.scores.get(2).score())) == 10;
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
