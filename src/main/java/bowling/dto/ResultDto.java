package bowling.dto;

import java.util.List;

public class ResultDto {

  private final String name;

  private final List<ScoreResultDto> frames;

  private ResultDto(final String name, final List<ScoreResultDto> frames) {
    this.name = name;
    this.frames = frames;
  }

  public static ResultDto of(final String name, final List<ScoreResultDto> resultList) {
    return new ResultDto(name, resultList);
  }

  public String getName() {
    return name;
  }

  public List<ScoreResultDto> getFrames() {
    return frames;
  }

  public int frameCount() {
    return frames.size();
  }
}
