package bowling;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BowlingGameResult {

  private Map<Integer, String> gameResult = new HashMap<>();
  private Map<Integer, Integer> gameScore = new HashMap<>();

  public BowlingGameResult(LinkedList<Frame> frames) {
    frames.stream()
        .forEach(frame -> record(frame));
  }

  private void record(Frame frame) {
    gameResult.put(frame.getFrameNo(), frame.toString());
    gameScore.put(frame.getFrameNo(), frame.score());
  }

  public String result(int frameNo) {
    return gameResult.get(frameNo);
  }

  public boolean hasResult(int frameNo) {
    return gameResult.containsKey(frameNo);
  }

  public boolean hasScore(int frameNo) {
    return gameScore.containsKey(frameNo);
  }

  public int score(int frameNo) {
    return gameScore.get(frameNo);
  }
}
