package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.state.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameResult {
  private static final Map<Integer, List<State>> result = new HashMap<>();

  static {
    for (int i = 1; i<= 10; i++) {
      result.put(i, new ArrayList<>());
    }
  }

  public void add(int playCount, State state) {
    result.get(playCount).add(state);
  }

  public Map<Integer, List<State>> getFrames() {
    return Collections.unmodifiableMap(result);
  }
}
