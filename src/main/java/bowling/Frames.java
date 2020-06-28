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
}
