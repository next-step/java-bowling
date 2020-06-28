package bowling;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

  private static final String NEWLINE = System.lineSeparator();

  private Deque<Frame> frames = new ArrayDeque<>();

  public Frames() {
    frames.add(new Frame());
  }

  public List<Frame> getFrames() {
    return Collections.unmodifiableList(frames.stream().collect(Collectors.toList()));
  }

  public int getSize() {
    return frames.size();
  }

  /**
   * 투구(roll a ball), FrameOverException 발생 시 새로운 프레임에서 진행
   *
   * @param knockDownNum
   */
  public void roll(int knockDownNum) {
    try {
      frames.peekLast().roll(knockDownNum);
    } catch (FrameOverException e) {
      addAndRoll(knockDownNum);
    }
  }

  private void addAndRoll(int knockDownNum) {
    frames.addLast(new Frame());

    try {
      frames.peekLast().roll(knockDownNum);
    } catch (FrameOverException frameOverException) {
      throw new IllegalStateException("roll 실패 "
          + NEWLINE + " frames : " + frames.toString()
          + NEWLINE + " knockDownNum : " + knockDownNum);
    }
  }

  public boolean isOver() {
    if (BowlingGame.MAX_NUMBER_OF_FRAMES <= frames.size() && frames.peekLast().isOver()) {
      return true;
    }

    return false;
  }

  @Override
  public String toString() {
    return "Frames{" +
        "frames=" + frames +
        '}';
  }
}
