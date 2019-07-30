package bowling;

import java.util.LinkedList;

public class Frames {

  private static final int LIST_INDEX_AND_FRAME_NO_DIFF = 1;

  private LinkedList<Frame> frames = new LinkedList<>();

  public Frames() {
    frames.add(NormalFrame.first());
  }

  public Frame bowl(int countOfPin) {
    Frame frame = currentFrame().bowl(new Pins(countOfPin));
    if (!isCurrentFrame(frame)) {
      frames.add(frame);
    }
    return currentFrame();
  }

  public Frame currentFrame() {
    return frames.getLast();
  }

  public int currentFrameNo() {
    return frames.getLast().frameNo();
  }

  private boolean isCurrentFrame(Frame frame) {
    return frame.equals(currentFrame());
  }

  public String desc(int frameNo) {
    return frames.get(frameNo - LIST_INDEX_AND_FRAME_NO_DIFF).desc();
  }

  public GameResult getResult() {
    return new BowlingGameResult(frames);
  }

  public boolean isGameEnd() {
    return currentFrame().isGameEnd();
  }
}
