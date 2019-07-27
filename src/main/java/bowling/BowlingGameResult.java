package bowling;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BowlingGameResult {

  private Map<Integer, String> gameResult = new HashMap<>();

  public BowlingGameResult(LinkedList<Frame> frames) {
    frames.stream()
        .forEach(frame -> record(frame.getFrameNo(), frame.toString()));
  }

  private void record(int frameNo, String result) {
    gameResult.put(frameNo, result);
  }

  public String result(int frameNo) {
    return gameResult.get(frameNo);
  }

  public boolean hasResult(int frameNo) {
    return gameResult.containsKey(frameNo);
  }
}
