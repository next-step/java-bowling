package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

  private List<Frame> frames = new ArrayList<>();

  public Frames() {
    frames.add(new NormalFrame());
    IntStream.range(1, 9).forEach(i -> addNormalFrame(i));
    addFinalFrame();
  }

  private void addNormalFrame(int i) {
    NormalFrame before = (NormalFrame) frames.get(i - 1);
    frames.add(before.next());
  }

  private void addFinalFrame() {
    NormalFrame normalFrame = (NormalFrame) frames.get(size() - 1);
    frames.add(normalFrame.last());
  }

  public int size() {
    return frames.size();
  }

  public List<String> play(int hitCount) {
    frames.stream().filter(frame -> !frame.isEnd())
        .findFirst().map(frame -> frame.play(hitCount))
        .orElseThrow(() -> new IllegalArgumentException("game is over."));
    return result();
  }


  public List<String> result() {
    return frames.stream().map(Frame::result).collect(Collectors.toList());
  }

  public Integer getCurrent() {
    return frames.stream().filter(frame -> !frame.isEnd())
        .findFirst().map(frame -> frame.number)
        .orElseThrow(() -> new IllegalArgumentException("game is over."));
  }

  public boolean isEnd() {
    return !frames.stream()
        .filter(frame -> !frame.isEnd())
        .findAny().isPresent();
  }
}
