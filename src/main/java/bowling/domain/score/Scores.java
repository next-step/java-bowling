package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final static int MAX_SCORE = 10;
    private final static int FIRST_SCORE_INDEX = 0;
    private final static int SECOND_SCORE_INDEX = 1;

    private final List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void addScore(int point) {
        if (firstScore()) {
            this.scores.add(Score.generateFirstScore(point));
            return;
        }

        if (secondScore()) {
            this.scores.add(Score.generateSecondScore(scores.get(FIRST_SCORE_INDEX), point));
        }

        if (totalScore() > MAX_SCORE) {
            throw new IllegalArgumentException("score not valid");
        }
    }

    public int totalScore() {
        return scores.stream()
                .mapToInt(Score::getPoint)
                .sum();
    }

    public boolean secondScore() {
        return scores.size() == SECOND_SCORE_INDEX;
    }

    public boolean firstScore() {
        return scores.size() == FIRST_SCORE_INDEX;
    }
}
