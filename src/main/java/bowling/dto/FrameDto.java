package bowling.dto;

import bowling.frame.Frame;
import java.util.Objects;

public class FrameDto {

  private final Frame frame;

  private FrameDto(final Frame frame) {
    this.frame = frame;
  }

  public static FrameDto from(final Frame frame){
    return new FrameDto(frame);
  }

  public Frame getFrame() {
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