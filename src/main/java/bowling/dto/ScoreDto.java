package bowling.dto;

public class ScoreDto {

  private final int score;

  private ScoreDto(final int score) {
    this.score = score;
  }

  public static ScoreDto from(int score){
    return new ScoreDto(score);
  }

  public int getScore() {
    return score;
  }
}
