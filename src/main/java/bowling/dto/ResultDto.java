package bowling.dto;

import bowling.frame.Frame;
import java.util.List;

public class ResultDto {

  private final String name;

  private final List<Frame> frames;

  private ResultDto(final String name, final List<Frame> frames) {
    this.name = name;
    this.frames = frames;
  }

  public static ResultDto of(final String name, final List<Frame> resultList) {
    return new ResultDto(name, resultList);
  }

  public String getName() {
    return name;
  }

  public List<Frame> getFrames() {
    return frames;
  }
}
