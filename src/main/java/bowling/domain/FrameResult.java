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
  private static final int MIN_PLAY_COUNT = 1;
  private static final int MAX_PLAY_COUNT = 10;

  static {
    for (int index = MIN_PLAY_COUNT; index<= MAX_PLAY_COUNT; index++) {
      result.put(index, new ArrayList<>());
    }
  }

  public void add(int playCount, State state) {
    result.get(playCount).add(state);
  }

  public Map<Integer, List<State>> getResult() {
    return Collections.unmodifiableMap(result);
  }
}
