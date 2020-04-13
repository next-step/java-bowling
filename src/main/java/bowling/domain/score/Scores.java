package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

/**
 * 각 프레임에서 기록한 점수들를 저장한다.
 */
public class Scores {
    private final List<Score> scores;

    public Scores() {
        scores = new ArrayList<>();
    }

    public void add(Score score) {
        scores.add(score);
    }

    public int size() {
        return scores.size();
    }

    public int currentPoint() {
        return scores.stream()
                .mapToInt(Score::getPoint)
                .sum();
    }

    public boolean isStrike(int index) {
        return scores.get(index).isEqualScoreType(ScoreType.STRIKE);
    }

    public boolean isSpare(int index) {
        return scores.get(index).isEqualScoreType(ScoreType.SPARE);
    }

    public String pointToScore(int scoreIndex) {
        return scores.get(scoreIndex).pointToScore();
    }

    public List<Score> getScores() {
        return scores;
    }
}
