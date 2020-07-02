package bowling.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Frames {

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
   * @param knockDownNumber 넘어뜨린 개수
   */
  public void roll(int knockDownNumber) {
    try {
      frames.getLast().roll(knockDownNumber);
    } catch (FrameOverException e) {
      addAndRoll(knockDownNumber);
    }
  }

  private void addAndRoll(int knockDownNumber) {
    frames.add(frames.getLast().next());

    try {
      frames.getLast().roll(knockDownNumber);
    } catch (FrameOverException frameOverException) {
      throw new IllegalStateException("roll 실패 "
          + System.lineSeparator() + " frames : " + frames.toString()
          + System.lineSeparator() + " knockDownNum : " + knockDownNumber);
    }
  }

  public boolean isCurrentFrameOver() {
    return frames.getLast().isOver();
  }

  public boolean isOver() {
    return frames.getLast().isFinished();
  }

  public List<Score> getScores() {
    List<Score> scores = new ArrayList<>();
    List<Frame> frames = getFrames();

    frames.stream()
        .limit(BowlingGame.MAX_NUMBER_OF_FRAMES)
        .forEach(frame -> {
          scores.add(frame.getScore());
        });
    return scores;
  }

  @Override
  public String toString() {
    return "Frames{" +
        "frames=" + frames +
        '}';
  }
}
