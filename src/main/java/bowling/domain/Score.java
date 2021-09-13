package bowling.domain;

import bowling.exception.InvalidScoreException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Score implements Comparable<Score> {
    public static final int ZERO_SCORE = 0;
    public static final int TEN_SCORE = 10;
    public static final String STRIKE_SYMBOL = "X";
    public static final String SPARE_SYMBOL = "/";
    public static final String GUTTER_SYMBOL = "-";
    public static final String SEPARATOR_SYMBOL = "|";

    private static final String INVALID_SCORE_MESSAGE = "핀의 갯수가 10을 초과할 수 없습니다.";

    private static Map<Integer, Score> scoresMap = new HashMap<>();
    private final int score;

    static {
        for (int number = ZERO_SCORE; number <= TEN_SCORE; number++) {
            scoresMap.put(number, new Score(number));
        }
    }

    public Score(int score) {
        this.score = score;
    }

    public static Score valueOf(int score) {
        validate(score);
        return scoresMap.get(score);
    }

    public String changeScoreToSymbol() {
        if (score == ZERO_SCORE) {
            return GUTTER_SYMBOL;
        }
        if (score == TEN_SCORE) {
            return STRIKE_SYMBOL;
        }
        return score + "";
    }

    public String changeScoreToSpare() {
        return changeScoreToSymbol() + SEPARATOR_SYMBOL + SPARE_SYMBOL;
    }

    private static void validate(int score) {
        if (score < ZERO_SCORE || score > TEN_SCORE) {
            throw new InvalidScoreException(INVALID_SCORE_MESSAGE);
        }
    }

    public int sum(Score score) {
        return this.score + score.score;
    }

    public static int sumOfScores(List<Score> scores) {
        return scores.stream().mapToInt(s -> s.score).sum();
    }

    public static void validateTotalScore(List<Score> scores, Score score) {
        int sum = sumOfScores(scores);
        if (sum < TEN_SCORE) {
            validate(sum + score.score);
        }
    }

    public int score() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public int compareTo(Score o) {
        return this.score - o.score;
    }
}
