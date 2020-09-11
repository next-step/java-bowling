package bowling.domain;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Bowling {

  public static final int FRAME_COUNT = 10;
  private SortedSet<Frame> frames = new TreeSet<>();

  private Bowling(Frame frame) {
    frames.add(frame);
  }

  public static Bowling first(int pins) {
    Frame frame = new Frame(1);
    frame.roll(pins);
    return new Bowling(frame);
  }

  public void roll(int pins) {
    if (nextFrame() <= FRAME_COUNT) {
      frames.add(frames.last().roll(pins));
    }
  }

  public int nextFrame() {
    return (int) frames.stream()
        .filter(Frame::isDone)
        .count() + 1;
  }

  public List<String> symbols() {
    return frames.stream()
        .map(Frame::symbol)
        .collect(Collectors.toList());
  }

}
