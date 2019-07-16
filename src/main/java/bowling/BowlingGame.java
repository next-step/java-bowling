package bowling;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BowlingGame {

  Frame frame = NormalFrame.first();
  Map<Integer,String> result = new HashMap<>();

  public Frame roll(int countOfPin) {
    frame = frame.roll(countOfPin);

    result.put(frame.getFrameNo(),frame.toString());
    return frame;
  }

  public Boolean isGameEnd() {
    return frame.isGameEnd();
  }

  public int currentFrameNo() {
    return frame.getFrameNo();
  }

  public Map<Integer,String> getResult() {
    return Collections.unmodifiableMap(result);
  }

  @Override
  public String toString() {
    return frame.toString();
  }
}
