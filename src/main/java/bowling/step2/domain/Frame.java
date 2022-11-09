package bowling.step2.domain;

import bowling.step2.utils.GameRuleUtils;

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
        if (index != GameRuleUtils.GAME_LAST_INDEX && isContainStrike()) {
            return false;
        }
        return true;
    }

    public boolean hasBonus(int index) {
        if (index == GameRuleUtils.GAME_LAST_INDEX && (isContainStrike() || isSpare())) {
            return true;
        }
        return false;
    }

    private boolean isContainStrike() {
        return this.scores.stream().anyMatch(it -> it.isStrike());
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
        return scores;
    }
}
