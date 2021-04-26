package bowling_step4.domain.frame;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;
import bowling_step4.domain.Scores;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

  public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";
  private final List<Frame> frames = new ArrayList<>();

  public Frames() {
    frames.add(NormalFrame.createFirst());
  }

  public void play(Pin pinCount) {
    if (isEnd()) {
      throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    Frame frame = getLastFrame().play(pinCount);

    if (isFinalFrame()) {
      return;
    }

    if (getLastFrame().isEnd()) {
      frames.add(frame);
    }

  }


  private boolean isFinalFrame() {
    return getLastFrame().getPlayCount() == 9;
  }

  public boolean isEnd() {
    return isFinalFrame() && getLastFrame().isEnd();
  }

  private Frame getLastFrame() {
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

  public List<Score> getScores() {

    Scores scores = new Scores();
    for (Frame frame : frames) {
      scores.add(frame);
    }

    return scores.getScores();

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
