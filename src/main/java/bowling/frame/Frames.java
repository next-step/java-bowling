package bowling.frame;

import java.util.LinkedList;
import java.util.List;

public class Frames {

  private static final int FIRST_FRAME = 1;

  private final LinkedList<Frame> frames;

  public Frames() {
    this.frames = new LinkedList<>();
    frames.add(new NormalFrame(FIRST_FRAME));
  }

  public void pitch(final int pin) {

    Frame currentFrame = frames.getLast().play(pin);
    if (!currentFrame.equals(frames.getLast())) {
      frames.add(currentFrame);
    }
  }

  public int size() {
    return frames.size();
  }

  public List<Frame> resultList() {
    return frames;
  }

  public boolean isEnd() {
    return frames.getLast().isGameEnd();
  }

}