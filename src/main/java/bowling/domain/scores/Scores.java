package bowling.domain.scores;

import bowling.domain.score.EmptyScore;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 각 프레임에서 기록한 점수들를 저장한다.
 */
public class Scores {
    private static final int ONE = 1;
    private static final int SECOND_PLAY = 1;
    private static final int THIRD_PLAY = 2;
    private static final int DEFAULT_PLAY_COUNT = 2;
    private static final int BONUS_PLAY_COUNT = 3;

    private final List<Score> scores;

    public Scores() {
        scores = new ArrayList<>();
    }

    public void add(int point) {
        scores.add(createScore(point));
    }

    private Score createScore(int point) {
        if (CollectionUtils.isEmpty(scores)) {
            return EmptyScore.nextScore(point);
        }
        return getLastScore().nextScore(point);
    }

    private Score getLastScore() {
        return scores.get(scores.size() - ONE);
    }

    public int currentPoint() {
        return scores.stream()
                .mapToInt(Score::getPoint)
                .sum();
    }

    public boolean isStrike(int index) {
        return scores.get(index).isEqualScoreType(ScoreType.STRIKE);
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

    public boolean isPlayable() {
        return scores.size() < DEFAULT_PLAY_COUNT;
    }

    public boolean isBonusPlayable() {
        return scores.size() < BONUS_PLAY_COUNT;
    }
}
