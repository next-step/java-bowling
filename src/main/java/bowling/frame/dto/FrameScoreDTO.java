package bowling.frame.dto;

public class FrameScoreDTO {

    private int score;
    private boolean isCalculated;

    private FrameScoreDTO(int score, boolean isCalculated) {
        this.score = score;
        this.isCalculated = isCalculated;
    }

    public static FrameScoreDTO of(int score, boolean isCalculated) {
        return new FrameScoreDTO(score, isCalculated);
    }

    public int getScore() {
        return score;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

}
