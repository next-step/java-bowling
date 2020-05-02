package bowling.domain.frame;

import static bowling.domain.bowlresult.BonusResult.NULL_RESULT;

import bowling.domain.FrameData;
import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.bowlresult.RegularResult;
import bowling.domain.Round;
import bowling.domain.Score;
import bowling.domain.framestate.FrameState;
import bowling.exception.CannotBowlException;

public class NormalFrameNode implements FrameNode {

  private FrameData frameData;
  private FrameNode next;

  private NormalFrameNode(Round round) {
    frameData = new FrameData(round);
    next = NullFrameNode.of(round.next());
  }

  public static NormalFrameNode initialize() {
    return new NormalFrameNode(Round.first());
  }

  public static NormalFrameNode of(Round round) {
    return new NormalFrameNode(round);
  }

  public int getRolledBowlCount() {
    return frameData.getRolledBowlCount();
  }

  @Override
  public void addFrameResult(FrameResults frameResults) {
    frameResults.add(new FrameResult(frameData.getRegularResult(), NULL_RESULT, calculateScore()));
  }

  @Override
  public FrameNode getNextFrame() {
    return next;
  }

  @Override
  public FrameNode roll(int pinCount) throws CannotBowlException {
    frameData.roll(pinCount);
    if (frameData.isFinished()) {
      next = FrameNode.of(frameData.nextRound());
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
    RegularResult regularResult = frameData.getRegularResult();
    Score score = regularResult.getScore(bonusBowlCount);

    return Score.add(score, additionalScore);
  }

  @Override
  public Score calculateScore() {
    RegularResult regularResult = frameData.getRegularResult();
    FrameState frameState = regularResult.getState();
    Score score = frameData.getScore();

    return Score.add(score, next.calculateBonusScore(frameState.getBonusBallCount()));
  }

  @Override
  public boolean isFinished() {
    return frameData.isFinished();
  }

  @Override
  public boolean isFinalFrame() {
    return false;
  }
}
