package bowling.domain.score;

public class Score {
    private int score;
    private int leftCount;

    public static Score ofMiss(int point) {
        return new Score(point, 0);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    private Score(int score, int leftCount) {
        this.score = score;
        this.leftCount = leftCount;
    }

    public Score calculate(int point) {
        if (leftCount != 0) {
            return new Score(score += point, leftCount - 1);
        }
        return this;
    }

    public Integer getScore() {
        if (!canCalcucateScore()) {
            throw new ScroeAccessDenyException("아직 스코어에 접근 할 수 없습니다.");
        }
        return this.score;
    }

    public boolean canCalcucateScore() {
        return leftCount == 0;
    }
}
