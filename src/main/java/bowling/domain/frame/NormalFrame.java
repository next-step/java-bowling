package bowling.domain.frame;

import bowling.domain.FrameNode;
import bowling.domain.RegularResult;
import bowling.domain.Round;
import bowling.domain.Score;
import bowling.domain.framestate.FrameState;
import bowling.exception.CannotBowlException;

public class NormalFrame implements FrameNode {

  private Frame frame;
  private FrameNode next;

  private NormalFrame(Round round) {
    frame = new Frame(round);
    next = NullFrame.of(round.next());
  }

  public static NormalFrame initialize() {
    return new NormalFrame(Round.first());
  }

  public static NormalFrame of(Round round) {
    return new NormalFrame(round);
  }

  public Frame getFrame() {
    return frame;
  }

  @Override
  public int getRolledBowlCount() {
    return frame.getRolledBowlCount();
  }

  @Override
  public FrameNode getNextFrame() {
    return next;
  }

  @Override
  public FrameNode roll(int pinCount) throws CannotBowlException {
    frame.roll(pinCount);
    if (frame.isFinished()) {
      next = FrameNode.of(frame.nextRound());
      return next;
    }

    return this;
  }

  @Override
  public Score calculateBonusScore(int bonusBowlCount) {
    if (bonusBowlCount <= NO_BONUS_BOWL) {
      return Score.zero();
    }

    Score additionalScore = next.calculateBonusScore(bonusBowlCount - getRolledBowlCount());
    RegularResult regularResult = frame.getRegularResult();
    Score score = regularResult.getScore(bonusBowlCount);

    return Score.add(score, additionalScore);
  }

  @Override
  public Score calculateScore() {
    RegularResult regularResult = frame.getRegularResult();
    FrameState frameState = regularResult.getState();
    Score score = frame.getScore();

    return Score.add(score, next.calculateBonusScore(frameState.getBonusBallCount()));
  }

  @Override
  public boolean isFinished() {
    return frame.isFinished();
  }

  @Override
  public boolean isFinalFrame() {
    return false;
  }
}
