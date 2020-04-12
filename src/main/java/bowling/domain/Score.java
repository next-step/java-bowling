package bowling.domain;

//스코어 : 0 ~ 10
//스코어 : 같은 프레임의 스코어 합이 10을넘으면안됨



public class Score {
    private static final int FRAME_MAX_SCORE = 10;
    private static final int FRAME_MIN_SCORE = 0;
    private int score = 0;

    public Score(int score) {
        checkScore(score);
        this.score = score;
    }

    private void checkScore(int score) {
        if (score < FRAME_MIN_SCORE || score > FRAME_MAX_SCORE){
            throw new IllegalArgumentException(FRAME_MIN_SCORE + "~" + FRAME_MAX_SCORE + " 입력");
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isMaxScore() {
        return score == FRAME_MAX_SCORE;
    }

    public boolean isMinScore() {
        return score == FRAME_MIN_SCORE;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                '}';
    }
}
