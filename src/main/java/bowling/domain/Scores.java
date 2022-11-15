package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Scores {
    private final List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public Scores(Score... scores) {
        this.scores = Arrays.stream(scores)
                .collect(Collectors.toList());
    }

    public void addBonusScore(Pin pin) {
        Score previous = null;
        for (Score score : scores) {
            addBonusAndPrevious(pin, previous, score);
            previous = score;
        }
    }

    private void addBonusAndPrevious(Pin pin, Score previous, Score now) {
        if (!now.isEnd()) {
            now.addBonus(pin);
            addPreviousIfEndAddBonus(previous, now);
        }
    }

    public void add(Score score) {
        Score lastScore = findLastScore();
        addPreviousIfEndAddBonus(lastScore, score);
        scores.add(score);
    }

    private Score findLastScore() {
        if (scores.size() != 0) {
            return scores.get(scores.size() - 1);
        }
        return null;
    }

    private void addPreviousIfEndAddBonus(Score previous, Score now) {
        if (Objects.isNull(previous)) {
            return;
        }

        if (now.isEnd()) {
            now.add(previous);
        }
    }

    public List<Score> getScores() {
        return scores;
    }
}
