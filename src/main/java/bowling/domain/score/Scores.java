package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

/**
 * 각 프레임에서 기록한 점수들를 저장한다.
 */
public class Scores {
    private static final int SECOND_PLAY = 1;
    private static final int THIRD_PLAY = 2;

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

    public List<Score> getScores() {
        return new ArrayList<>(scores);
    }

    public boolean hasStrikeOrSpare() {
        return scores.stream()
                .anyMatch(score -> score.isEqualScoreType(ScoreType.STRIKE)
                        || score.isEqualScoreType(ScoreType.SPARE));
    }

    public boolean isSecondPlay() {
        return scores.size() == SECOND_PLAY;
    }

    public boolean isThirdPlay() {
        return scores.size() == THIRD_PLAY;
    }
}
