package bowling.dto;

import java.util.Objects;

public class ScoreResultDto {

  private final String scoreMark;

  private final int totalScore;

  private final int score;

  private ScoreResultDto(final String scoreMark, final int totalScore, final int score) {
    this.scoreMark = scoreMark;
    this.totalScore = totalScore;
    this.score = score;
  }

  public static ScoreResultDto of(final String scoreMessage, final int totalScore,
      final int score) {
    return new ScoreResultDto(scoreMessage, totalScore, score);
  }

  public String getScoreMark() {
    return scoreMark;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public int getScore() {
    return score;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ScoreResultDto frameDto = (ScoreResultDto) o;
    return Objects.equals(scoreMark, frameDto.scoreMark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scoreMark);
  }
}