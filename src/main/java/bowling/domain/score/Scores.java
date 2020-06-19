package bowling.domain.score;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final static int FIRST_SCORE = 1;
    private final static int SECOND_SCORE = 2;
    private final static int THIRD_SCORE = 3;
    private final static int ONE = 1;

    private final List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void addScore(int point) {
        scores.add(createScore(point));
    }

    private Score createScore(int point) {
        if (CollectionUtils.isEmpty(scores)) {
            return Score.generateFirstScore(point);
        }

        int lastIndex = scores.size() - ONE;
        return Score.generateNextScore(scores.get(lastIndex), point);
    }

    public int totalScore() {
        return scores.stream()
                .mapToInt(Score::getPoint)
                .sum();
    }

    public boolean firstScore() {
        return scores.size() == FIRST_SCORE;
    }

    public boolean secondScore() {
        return scores.size() == SECOND_SCORE;
    }

    public boolean thirdScore() {
        return scores.size() == THIRD_SCORE;
    }

    public boolean isStrikeOrSpare() {
        return isStrike() || isSpare();
    }

    public boolean isStrike() {
        return scores.get(FIRST_SCORE - ONE).isStrike();
    }

    public boolean isSpare() {
        if (secondScore() || thirdScore()) {
            return scores.get(SECOND_SCORE - ONE).isSpare();
        }
        return false;
    }
}
