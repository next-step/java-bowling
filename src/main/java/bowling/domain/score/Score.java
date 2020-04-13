package bowling.domain.score;

import java.util.List;

/**
 * 점수를 나타내는 객체 쓰러트린 볼링핀 개수와 점수 타입을 가진다.
 * 10점을 넘을 수 없다.
 *
 */
public class Score {
    private ScoreType scoreType;
    private int point;

    private Score(ScoreType scoreType, int point) {
        validatePoint(point);

        this.scoreType = scoreType;
        this.point = point;
    }

    private void validatePoint(int point) {
        if (point > 10) {
            throw new IllegalArgumentException("점수는 10점을 넘을수 없습니다.");
        }

        if (point < 0) {
            throw new IllegalArgumentException("점수는 0점이상이어야 합니다.");
        }
    }

    public static Score defaultScore(Scores scores, int point) {
        return new Score(generateDefaultScoreType(scores, point), point);
    }

    private static ScoreType generateDefaultScoreType(Scores scores, int point) {
        if (scores.size() == 0 && point == 10) {
            return ScoreType.STRIKE;
        }
        return getSpareOrGutterType(scores, point);
    }

    public static Score lastScore(Scores scores, int point) {
        return new Score(generateLastScoreType(scores, point), point);
    }

    private static ScoreType generateLastScoreType(Scores scores, int point) {
        if (point == 10) {
            return ScoreType.STRIKE;
        }
        return getSpareOrGutterType(scores, point);
    }

    private static ScoreType getSpareOrGutterType(Scores scores, int point) {
        if (scores.size() == 1 && scores.currentPoint() + point == 10) {
            return ScoreType.SPARE;
        }
        if (point == 0) {
            return ScoreType.GUTTER;
        }
        return ScoreType.MISS;
    }

    public int getPoint() {
        return point;
    }

    public boolean isEqualScoreType(ScoreType scoreType) {
        return this.scoreType.equals(scoreType);
    }

    public String pointToScore() {
        return scoreType.getScore(point);
    }
}
