package bowling.domain.score;

public class Score {

    private int score; // 현재까지 점수
    private int left; // 남은 시도 횟수

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(Score score) {
        return new Score(score.score, score.left);
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
    }

    public static Score ofMiss(int score) {
        return new Score(score, 0);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public int getScore() {
        if (!canCalucateScore()) {
            return 0;
        }
        return this.score;
    }

    public boolean canCalucateScore() {
        return left == 0;
    }


}
