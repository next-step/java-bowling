package bowling.domain;

public class FrameScore {
    private static final int NOT_SCORE_STATE = -1;
    private final int score;
    private int totalScore;

    public FrameScore(int score) {
        this.score = score;
        this.totalScore =  -1;
    }

    public int addTotalScore(int beforeScore) {
//        if(isNotScore()) {
//            this.totalScore = this.score;
//            return this.totalScore;
//        }
        totalScore = this.score + beforeScore;
        return this.totalScore;
    }

    public boolean isNotScore() {
        return this.score == NOT_SCORE_STATE;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "FrameScore{" +
                "score=" + score +
                ", totalScore=" + totalScore +
                '}';
    }
}
