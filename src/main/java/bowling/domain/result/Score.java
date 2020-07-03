package bowling.domain.result;

import bowling.domain.status.CannotCalculateException;

public class Score {
    private int score; // 현재까지 점수
    private int left; // 남은 시도 횟수

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
    }

    public int getScore(){
//        if (!canCalculateScore()) {
//            throw new CannotCalculateException();
//        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

}
