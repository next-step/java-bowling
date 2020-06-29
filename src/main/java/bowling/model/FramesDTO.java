package bowling.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FramesDTO {

  private List<FrameDTO> frames;

  public FramesDTO(List<Frame> frames) {
    this.frames = frames.stream()
        .map(FrameDTO::createBy)
        .collect(Collectors.toList());
  }

  public List<FrameDTO> getFrames() {
    return Collections.unmodifiableList(frames);
  }

  @Override
  public String toString() {
    return "FramesDTO{" +
        "frames=" + frames +
        '}';
  }
}
