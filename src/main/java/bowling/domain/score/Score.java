package bowling.domain.score;

public class Score {

    protected static final int MAX_SCORE = 10;

    protected static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    private static boolean outOfRange(int score) {
        return score < 0 || score > MAX_SCORE;
    }

}
