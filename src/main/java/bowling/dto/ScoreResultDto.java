package bowling.dto;

public class ScoreResultDto {

  private final String scoreMessage;

  private final int totalScore;

  private ScoreResultDto(final String scoreMessage, final int totalScore) {
    this.scoreMessage = scoreMessage;
    this.totalScore = totalScore;
  }

  public static ScoreResultDto of(final String scoreMessage, final int totalScore) {
    return new ScoreResultDto(scoreMessage, totalScore);
  }

  public String getScoreMessage() {
    return scoreMessage;
  }

  public int getTotalScore() {
    return totalScore;
  }
}