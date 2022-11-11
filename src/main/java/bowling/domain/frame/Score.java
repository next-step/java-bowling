package bowling.domain.frame;

public class Score {
    private static final int UN_SCORE_VALUE = -1;

    private final int score;
    private final int left;

    public static Score needToMoreBowl() {
        return new Score(UN_SCORE_VALUE, UN_SCORE_VALUE);
    }

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public boolean canCalculateScore() {
        // TODO: 추가 점수 계산할 때 조건 바꿀 것
        // return left == 0;
        return left != UN_SCORE_VALUE;
    }

    public int getValue() {
        return score;
    }
}
