package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

  public static final int SECOND_FRAME_INDEX = 1;
  public static final int FINAL_FRAME_INDEX = 9;
  private List<Frame> frames = new ArrayList<>();

  public Frames() {
    NormalFrame FirstFrame = new NormalFrame();
    frames.add(FirstFrame);
    IntStream.range(SECOND_FRAME_INDEX, FINAL_FRAME_INDEX).forEach(index -> addNormalFrame(index));
    addFinalFrame();
  }

  private void addNormalFrame(int index) {
    NormalFrame before = (NormalFrame) frames.get(index - 1);
    frames.add(before.next());
  }

  private void addFinalFrame() {
    NormalFrame normalFrame = (NormalFrame) frames.get(size() - 1);
    frames.add(normalFrame.last());
  }

  public int size() {
    return frames.size();
  }

  public List<FrameScore> play(int hitCount) {
    frames.stream().filter(frame -> !frame.isEnd())
        .findFirst().map(frame -> frame.play(hitCount))
        .orElseThrow(() -> new IllegalArgumentException("game is over."));
    return result();
  }

  public List<FrameScore> result() {
    return frames.stream().map(Frame::result)
        .map(FrameScore::new)
        .collect(Collectors.toList());
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
