package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private static final int SECOND_TRIAL = 2;

    private final List<Score> scores = new ArrayList<>();

    public void add(Score score) {
        scores.add(score);
    }

    public boolean isTotalScoreHigherOrEqualThan(int number) {
        int sum = getNumbers().stream()
                .reduce(Integer::sum)
                .orElse(0);

        return sum >= number;
    }

    public boolean isAfterSecondScore() {
        return scores.size() > SECOND_TRIAL;
    }

    public boolean isSecondScore() {
        return scores.size() == SECOND_TRIAL;
    }

    private List<Integer> getNumbers() {
        return scores.stream()
                .map(Score::toInt)
                .collect(Collectors.toList());
    }
}
