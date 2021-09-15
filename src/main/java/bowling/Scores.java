package bowling;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private static final int SECOND_TRIAL = 2;

    private final List<Score> scores = new ArrayList<>();

    public void add(Score score) {
        scores.add(score);
    }

    public boolean isAfterSecondScore() {
        return scores.size() > SECOND_TRIAL;
    }

    public boolean isSecondScore() {
        return scores.size() == SECOND_TRIAL;
    }

    public int getSize() {
        return scores.size();
    }

    public boolean isTotalScoreHigherOrEqualThan(int number) {
        Score scoreSum = scores.stream()
                .reduce(Score::sum)
                .orElse(new Score(0));

        return scoreSum.isHigherOrEqualThan(new Score(number));
    }

    public Score sum(int count) {
        return scores.stream()
                .limit(count)
                .reduce(Score::sum)
                .orElse(new Score(0));
    }
}
