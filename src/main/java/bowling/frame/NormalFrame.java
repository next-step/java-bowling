package bowling.frame;

import bowling.score.Score;
import bowling.score.ScoreBoard;
import bowling.score.ScoreResult;
import bowling.state.State;
import bowling.state.StateFactory;

public class NormalFrame implements Frame {

  private static final int LIMIT_FRAME = 9;
  private static final int INCREASE_ROUND_NUMBER = 1;
  private static final int DEFAULT_SCORE = 0;
  private static final int NOT_SUM_SCORE_VALUE = -1;

  private final int roundNumber;

  private State state;

  private Frame nextFrame;

  public NormalFrame(final int round) {
    this.roundNumber = round;
    this.state = StateFactory.startPitch();
  }

  @Override
  public Frame play(final int pinCount) {
    state = state.nextPitch(pinCount);

    if (state.isFinish()) {
      nextFrame = createFrame();
      return nextFrame;
    }
    return this;
  }

  private Frame createFrame() {
    if (roundNumber < LIMIT_FRAME) {
      return new NormalFrame(roundNumber + INCREASE_ROUND_NUMBER);
    }
    return new FinalFrame();
  }

  @Override
  public String getScoreMessage() {
    return state.scoreMessage();
  }

  @Override
  public boolean isGameEnd() {
    return false;
  }

  @Override
  public Score score() {
    Score score = state.score();
    if (score.isFinishBallCount()) {
      return score;
    }
    return nextFrame.frameScoreAdd(score);
  }

  @Override
  public Score frameScoreAdd(final Score before) {
    Score score = state.calculateScore(before);
    if (score.isFinishBallCount()) {
      return score;
    }
    return nextFrame.frameScoreAdd(score);
  }

  @Override
  public int scoreValue() {
    try {
      return score().scoreValue().getScore();

    } catch (RuntimeException e) {
      return DEFAULT_SCORE;
    }
  }

  @Override
  public ScoreResult createScoreResult() {
    if (!state.isFinish()) {
      System.out.println("----1----");
      return new ScoreResult(state.scoreMessage(), NOT_SUM_SCORE_VALUE);
    }

    try {
      System.out.println("----2----");
      return new ScoreResult(state.scoreMessage(), scoreValue());
    } catch (RuntimeException e) {
      System.out.println("----3----");
      return new ScoreResult(state.scoreMessage(), NOT_SUM_SCORE_VALUE);
    }
  }

  @Override
  public ScoreBoard createScoreBoard() {
    ScoreBoard scoreBoard = new ScoreBoard();
    addScoreResult(scoreBoard);
    return scoreBoard;
  }

  @Override
  public void addScoreResult(final ScoreBoard scoreBoard) {
    scoreBoard.addScoreResult(createScoreResult());

    if(nextFrame !=null){
      nextFrame.addScoreResult(scoreBoard);
    }
  }
}