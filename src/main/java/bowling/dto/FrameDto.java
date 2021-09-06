package bowling.dto;

import java.util.Objects;

public class FrameDto {

  private final String scoreMark;

  private final int totalScore;

  private FrameDto(final String scoreMark, final int totalScore) {
    this.scoreMark = scoreMark;
    this.totalScore = totalScore;
  }

  public static FrameDto from(final ScoreResultDto scoreBoard) {
    return new FrameDto(scoreBoard.getScoreMessage(), scoreBoard.getTotalScore());
  }

  public String getScoreMark() {
    return scoreMark;
  }

  public int getTotalScore() {
    return totalScore;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final FrameDto frameDto = (FrameDto) o;
    return Objects.equals(scoreMark, frameDto.scoreMark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scoreMark);
  }
}