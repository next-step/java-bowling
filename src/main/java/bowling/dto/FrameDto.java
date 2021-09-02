package bowling.dto;

import java.util.Objects;

public class FrameDto {

  private final String frame;

  private FrameDto(final String frame) {
    this.frame = frame;
  }

  public static FrameDto from(final String frame) {
    return new FrameDto(frame);
  }

  public String frameScoreMark() {
    return frame;
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
    return Objects.equals(frame, frameDto.frame);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frame);
  }
}