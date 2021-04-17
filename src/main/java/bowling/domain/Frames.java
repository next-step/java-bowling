package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.state.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frames {

  private final List<Frame> frames = new ArrayList<>();
  private static final int MIN_PLAY_COUNT = 1;
  private static final int MAX_PLAY_COUNT = 10;


  public List<Frame> getFrames() {
    return Collections.unmodifiableList(frames);
  }
}
