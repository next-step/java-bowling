package bowling.view.dto;

public class ScoreDto {
    // nullable
    Integer score;

    public ScoreDto(Integer score) {
        this.score = score;
    }

    public String getScoreDescription(int sumOfScore) {
        return score != null ? Integer.toString(score + sumOfScore) : "";
    }

    public Integer getValue() {
        return score != null ? score : Integer.valueOf(0);
    }
}
