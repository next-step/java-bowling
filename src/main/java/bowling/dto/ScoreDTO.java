package bowling.dto;

public class ScoreDTO {
    private int score;
    private boolean isUnscoredScore;

    public ScoreDTO(int score, boolean isUnscoredScore) {
        this.score = score;
        this.isUnscoredScore = isUnscoredScore;
    }

    public int score() {
        return score;
    }

    public boolean isUnscoredScore() {
        return isUnscoredScore;
    }
}
