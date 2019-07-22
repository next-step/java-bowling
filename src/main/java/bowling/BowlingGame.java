package bowling;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BowlingGame {

  Frame frame = NormalFrame.first();
  Map<Integer, String> result = new HashMap<>();

  public Frame roll(int countOfPin) {
    Frame currentFrame = frame.roll(countOfPin);
    result.put(currentFrame.getFrameNo(), currentFrame.toString());
    frame = currentFrame.nextFrame();
    return frame;
  }

  public Boolean isGameEnd() {
    return frame.isGameEnd();
  }

  public int currentFrameNo() {
    return frame.getFrameNo();
  }

  public Map<Integer, String> getResult() {
    return Collections.unmodifiableMap(result);
  }

  @Override
  public String toString() {
    return frame.toString();
  }
}
