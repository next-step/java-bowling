package score;

import java.util.ArrayList;
import java.util.List;

public class BasicScores implements Scores {
    private static final String SUM_OF_SCORES_OVER_TEN = "점수들의 합은 10을 넘길 수 없습니다.";
    private List<Score> scores;

    public BasicScores() {
        this.scores = new ArrayList<>();
    }

    public List<Score> getScores() {
        return scores;
    }

    public void addScore(int score) {
        if (sumScore() + score > 10) {
            throw new IllegalArgumentException(SUM_OF_SCORES_OVER_TEN);
        }
        this.scores.add(Score.of(score));
    }

    public int sumScore() {
        return scores.stream()
                .map(Score::getScore)
                .reduce(Integer::sum).orElse(0);
    }

    public int size() {
        return scores.size();
    }
}
