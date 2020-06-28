package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

  public final static int MAX_NUMBER_OF_FRAMES = 10;

  List<Frame> frames;

  public BowlingGame() {
    frames = new ArrayList<>();
    frames.add(new Frame());
  }

  public void roll(int number) {
    try {
      frames.get(frames.size() - 1).roll(number);
    } catch (FrameOverException e) {
      frames.add(new Frame());
      try {
        frames.get(frames.size() - 1).roll(number);
      } catch (FrameOverException frameOverException) {
        frameOverException.printStackTrace();
      }
    }
  }

  public boolean requiredBonusFrame() {
    if (MAX_NUMBER_OF_FRAMES < frames.size() + 1 && frames.get(frames.size() - 1).isOver()) {
      return true;
    }

    return false;
  }

  @Override
  public String toString() {
    return "BowlingGame{" +
        "frames=" + frames +
        '}';
  }
}
