package bowling;

import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Frames {

  private static final int LIST_INDEX_AND_FRAME_NO_DIFF = 1;

  private LinkedList<Frame> frames = new LinkedList<>();

  public Frames() {
    frames.add(NormalFrame.first());
  }

  public Frames bowl(int countOfPin) {
    Frame frame = currentFrame().bowl(new Pins(countOfPin));
    if (!isCurrentFrame(frame)) {
      frames.add(frame);
    }
    return this;
  }

  private boolean isCurrentFrame(Frame frame) {
    return frame.equals(currentFrame());
  }

  public Frame currentFrame() {
    return frames.getLast();
  }

  public String desc(int frameNo) {
    return frames.get(frameNo - LIST_INDEX_AND_FRAME_NO_DIFF).desc();
  }

  public Score score(int frameNo) {
    return frames.get(frameNo - LIST_INDEX_AND_FRAME_NO_DIFF).getScore();
  }

  public GameResult getResult(String playerName) {
    List<FrameResult> collect = frames.stream()
        .map(frame -> new FrameResult(frame.desc(), frame.getScore().getScore()))
        .collect(toList());
    return new BowlingGameResult(collect, playerName);
  }

  public boolean isGameEnd() {
    return currentFrame().isGameEnd();
  }

  public int currentFrameNo() {
    return currentFrame().frameNo();
  }

  public boolean isOneFrameEnd(int currentFrameNo) {
    if (isGameEnd()) {
      return true;
    }
    return currentFrameNo() != currentFrameNo;
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
