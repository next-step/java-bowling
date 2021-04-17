package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.state.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameResult {

  private static final Map<Integer, List<State>> frameResult = new HashMap<>();

  static {
    for (int index = 1; index<= 10; index++) {
      frameResult.put(index, new ArrayList<>());
    }
  }


  public Map<Integer, List<State>> getFrames() {
    return Collections.unmodifiableMap(frameResult);
  }

  public void add(int playCount, State state) {
    frameResult.get(playCount).add(state);
  }
}
