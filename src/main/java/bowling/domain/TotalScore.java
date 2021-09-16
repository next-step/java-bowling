package bowling.domain;

public class TotalScore {
    private static final int STRIKE_LEFT = 2;
    private static final int SPARE_LEFT = 1;
    private static final int NONE_LEFT = 0;

    private int totalScore;
    private int left;

    public TotalScore(int score, int left) {
        this.totalScore = score;
        this.left = left;
    }

    public TotalScore(int score) {
        this.totalScore = score;
        this.left = NONE_LEFT;
    }

    public static TotalScore strikeTotalScore(int score) {
        return new TotalScore(score, STRIKE_LEFT);
    }

    public static TotalScore spareTotalScore(int score) {
        return new TotalScore(score, SPARE_LEFT);
    }

    public int getScore() {
        if (!canCalucateScore()) {
            throw new CannotCalculateException("점수를 계산할 수 없습니다.");
        }
        return this.totalScore;
    }

    public boolean canCalucateScore() {
        return left == NONE_LEFT;
    }

    public void calculate(int firstScore) {
        totalScore += firstScore;
        left --;
    }


}
