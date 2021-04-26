package bowling.domain.frame;


import bowling.domain.Pin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frames {

  public static final int MAX_PLAY_COUNT = 10;
  public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";
  private final List<Frame> frames;


  public Frames() {
    this.frames = new ArrayList<>();
  }

  public void play(Pin pinCount) {
    if (isEnd()) {
      throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    makeFrame();
    getLastFrame().play(pinCount);
    calculateScore(pinCount);
  }

  private void calculateScore(Pin pinCount) {
    for (Frame frame : frames) {
      int index = size() - 1;
      frame.calculateScore(index, pinCount);
    }
  }

  private void makeFrame() {
    if (frames.isEmpty()) {
      frames.add(NormalFrame.createFirst());
      return;
    }

    if (!getLastFrame().isEnd()) {
      return;
    }

    frames.add(nextFrame());
  }

  private Frame nextFrame() {
    return size() > NormalFrame.MAX_PLAY_COUNT ? new FinalFrame() : getLastFrame().next();
  }

  private boolean isFinalFrame() {
    return frames.size() == MAX_PLAY_COUNT;
  }

  public boolean isEnd() {
    return isFinalFrame() && getLastFrame().isEnd();
  }

  private Frame getLastFrame() {
    if (frames.isEmpty()) {
      throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    return frames.get(size() - 1);
  }

  public List<Frame> getFrames() {
    return Collections.unmodifiableList(frames);
  }

  public int getIndex() {
    if (frames.isEmpty() || getLastFrame().isEnd()) {
      return size() + 1;
    }

    return size();
  }

  public List<Integer> getScores() {
    return frames.stream()
        .filter(Frame::hasScore)
        .map(Frame::getScore)
        .collect(Collectors.toList());
  }

  public int size() {
    return frames.size();
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Frames frames1 = (Frames) o;
    return Objects.equals(frames, frames1.frames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frames);
  }


}
