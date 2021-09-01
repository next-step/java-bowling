package bowling.dto;

import java.util.List;

public class ResultDto {

  private final String name;

  private final List<FrameDto> frames;

  private ResultDto(final String name, final List<FrameDto> frames) {
    this.name = name;
    this.frames = frames;
  }

  public static ResultDto of(final String name, final List<FrameDto> resultList) {
    return new ResultDto(name, resultList);
  }

  public String getName() {
    return name;
  }

  public List<FrameDto> getFrames() {
    return frames;
  }
}
