package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final static int MAX_SCORE = 10;
    
    private final List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void addScore(Score score) {
        this.scores.add(score);
        if (totalScore() > MAX_SCORE) {
            throw new IllegalArgumentException("score not valid");
        }
    }

    private int totalScore() {
        return scores.stream()
                .mapToInt(Score::getPoint)
                .sum();
    }

}
