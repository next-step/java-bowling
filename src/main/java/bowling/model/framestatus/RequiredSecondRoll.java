package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;
import bowling.model.EmptyFrame;
import java.util.Collections;
import java.util.List;

public class RequiredSecondRoll implements FrameStatus {

  private final static Integer ZERO = 0;

  private final List<Integer> scoringFramesIndexes;

  public RequiredSecondRoll(FrameStatus frameStatus) {
    scoringFramesIndexes = frameStatus.getScoringFramesIndexes();
  }

  @Override
  public Frame getNextFrame() {
    return new EmptyFrame();
  }

  @Override
  public List<Integer> getScoringFramesIndexes() {
    return Collections.unmodifiableList(scoringFramesIndexes);
  }

  @Override
  public Score getAdditionalScore() {
    return new Score(0);
  }

  @Override
  public int getSizeOfScoringFramesIndexes() {
    return scoringFramesIndexes.size();
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    if (pins.getRemainingNumber() == ZERO) {
      return new Spare(this);
    }

    return new Miss(this);
  }

  @Override
  public boolean isOver() {
    return false;
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String getResultBy(KnockedDownPins pins) {
    return String.valueOf(pins.getFirstKnockDownNumber())
        .replace(ZERO.toString(), GUTTER.toString());
  }

  @Override
  public boolean isFinished() {
    return false;
  }


  @Override
  public String toString() {
    return "RequiredSecondRoll{" +
        "indexOfScoredFrames=" + scoringFramesIndexes +
        '}';
  }
}
