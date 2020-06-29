package camp.nextstep.edu.nextstep8.bowling;

public class Score {
    private static final int MAX_SCORE = 10;

    private final int score;

    public Score(int score) {
        validateRange(score);
        this.score = score;
    }

    public boolean hasMoreScore(int spare) {
        return MAX_SCORE < (score + spare);
    }

    public int getScore() {
        return this.score;
    }

    public boolean meetMaxScore() {
        return meetMaxScore(0);
    }

    public boolean meetMaxScore(int spare) {
        return MAX_SCORE == (this.score + spare);
    }

    private void validateRange(int score) {
        if(MAX_SCORE < score) {
            throw new IllegalArgumentException(MAX_SCORE + "점 을 넘을 수 없습니다");
        }
    }
 }
