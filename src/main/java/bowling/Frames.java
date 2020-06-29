package bowling;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Frames {

  private static final String NEWLINE = System.lineSeparator();
  private static final int FIRST_INDEX = 0;

  private final Deque<Frame> frames = new ArrayDeque<>();

  public Frames() {
    frames.add(new NormalFrame(FIRST_INDEX));
  }

  public List<Frame> getFrames() {
    return Collections.unmodifiableList(new ArrayList<>(frames));
  }

  public int getSize() {
    return frames.size();
  }

  /**
   * 투구(roll a ball), FrameOverException 발생 시 새로운 프레임에서 진행
   *
   * @param knockDownNum 넘어뜨린 개수
   */
  public void roll(int knockDownNum) {
    try {
      frames.getLast().roll(knockDownNum);
    } catch (FrameOverException e) {
      addAndRoll(knockDownNum);
    }
  }

  private void addAndRoll(int knockDownNum) {
    frames.addLast(new NormalFrame(frames.size()));

    try {
      frames.getLast().roll(knockDownNum);
    } catch (FrameOverException frameOverException) {
      throw new IllegalStateException("roll 실패 "
          + NEWLINE + " frames : " + frames.toString()
          + NEWLINE + " knockDownNum : " + knockDownNum);
    }
  }

  public boolean isOver() {
    return BowlingGame.MAX_NUMBER_OF_FRAMES <= frames.size() && frames.peekLast().isOver();
  }

  @Override
  public String toString() {
    return "Frames{" +
        "frames=" + frames +
        '}';
  }

  public void bonusRoll(int knockDownNum) {
    Frame bonusFrame = new BonusFrame();

    try {
      bonusFrame.roll(knockDownNum);
    } catch (FrameOverException e) {
      new IllegalStateException();
    }

    frames.addLast(bonusFrame);
  }
}
