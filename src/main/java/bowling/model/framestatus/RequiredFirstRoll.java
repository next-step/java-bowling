package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;
import bowling.model.EmptyFrame;
import java.util.Collections;
import java.util.List;

public class RequiredFirstRoll implements FrameStatus {

  private final List<Integer> scoringFramesIndexes;

  public RequiredFirstRoll(int currentIndex) {
    scoringFramesIndexes = Collections.singletonList(currentIndex);
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
    if (pins.getFirstKnockDownNumber() == KnockedDownPins.MAX_NUMBER_OF_PINS) {
      return new Strike(this);
    }

    return new RequiredSecondRoll(this);
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
    return BLANK.toString();
  }

  @Override
  public boolean isFinished() {
    return false;
  }


  @Override
  public String toString() {
    return "RequiredFirstRoll{" +
        "indexOfScoredFrames=" + scoringFramesIndexes +
        '}';
  }
}
