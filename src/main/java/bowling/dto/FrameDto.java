package bowling.dto;

import java.util.Objects;

public class FrameDto {

  private final String scoreMark;

  private FrameDto(final String scoreMark) {
    this.scoreMark = scoreMark;
  }

  public static FrameDto from(final String scoreMark) {
    return new FrameDto(scoreMark);
  }

  public String frameScoreMark() {
    return scoreMark;
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