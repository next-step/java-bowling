package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.BowlingGame;
import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.NormalFrame;
import bowling.model.Score;
import java.util.Collections;
import java.util.List;

public class Miss implements FrameStatus {

  private final List<Integer> scoringFramesIndexes;
  private int currentIndex;

  private Frame nextFrame;

  public Miss(FrameStatus frameStatus) {
    currentIndex = frameStatus.getScoringFramesIndexes().get(0);
    scoringFramesIndexes = frameStatus.getScoringFramesIndexes();

    nextFrame = new NormalFrame(currentIndex + 1);
  }

  @Override
  public Frame getNextFrame() {
    return nextFrame;
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
    return this;
  }

  @Override
  public boolean isOver() {
    return true;
  }

  @Override
  public String getResultBy(KnockedDownPins pins) {
    if (pins.getRemainingNumber() == 0) {
      throw new IllegalArgumentException("미스가 아닙니다.");
    }

    return (pins.getFirstKnockDownNumber() + BAR.toString() + pins.getSecondKnockDownNumber())
        .replaceAll("0", GUTTER.toString());
  }

  @Override
  public boolean isFinished() {
    return currentIndex == BowlingGame.MAX_NUMBER_OF_FRAMES - 1;
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String toString() {
    return "Miss{" +
        "indexOfScoredFrames=" + scoringFramesIndexes +
        '}';
  }
}
