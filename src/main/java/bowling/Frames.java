package bowling;

import java.util.LinkedList;

public class Frames {

  private LinkedList<Frame> frames;

  public Frames() {
    this.frames = new LinkedList<>();
    frames.add(NormalFrame.first());
  }

  public Frame roll(int countOfPin) {
    Frame currentFrame = currentFrame().roll(countOfPin);
    return saveFrame(currentFrame);

  }

  private Frame saveFrame(Frame currentFrame) {
    if (currentFrame.isGameEnd()) {
      frames.add(currentFrame.nextFrame());
    }
    return currentFrame();
  }

  public Boolean isGameEnd() {
    return currentFrame().isGameEnd();
  }

  private Frame currentFrame() {
    return frames.getLast();
  }

  public BowlingGameResult getResult() {
    return new BowlingGameResult(frames);
  }

  public LinkedList<Frame> getFrames() {
    return frames;
  }

  public int getFrameNo() {
    return currentFrame().getFrameNo();
  }

  @Override
  public String toString() {
    return currentFrame().toString();
  }
}
