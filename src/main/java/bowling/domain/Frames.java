package bowling.domain;

import bowling.domain.frame.Frame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
  private final List<Frame> frames = new ArrayList<>();

  public void add(Frame frame) {
    frames.add(frame);
  }

  public List<Frame> getFrames() {
    return Collections.unmodifiableList(frames);
  }
}
