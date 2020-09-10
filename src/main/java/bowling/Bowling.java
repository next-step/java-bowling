package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

  private List<Frame> frames = new ArrayList<>();

  Bowling(Frame frame) {
    frames.add(frame);
  }

  public static Bowling first(int pins) {
    Frame frame = new Frame(1);
    frame.roll(pins);
    return new Bowling(frame);

  }

  public void roll(int pins) {
    Frame last = frames.get(frames.size() - 1);
    Frame next = last.roll(pins);

    if (!last.equals(next)) {
      frames.add(next);
    }
  }

  public int nextFrame() {
    return (int) frames.stream()
        .filter(Frame::isDone)
        .count() + 1;
  }
}
