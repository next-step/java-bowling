package bowling.domain;

public class Score {
    private int score = 0;

    public Score(int score) {
        checkScore(score);
        this.score = score;
    }

    private void checkScore(int score) {
        if (score < 0 || score > 10){
            throw new IllegalArgumentException("0~10 입력");
        }
    }
}
