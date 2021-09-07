package bowling.frame;

import bowling.exception.ScoreCalculateException;
import bowling.score.Score;
import bowling.score.ScoreBoard;
import bowling.score.ScoreResult;
import bowling.state.State;
import bowling.state.StateFactory;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

  private static final int START_STATES_INDEX = 1;
  private static final String MSG_ERROR_END_FRAME = "이미 종료된 프레임입니다.";
  private static final String SEPARATOR = "|";
  private static final int NOT_SUM_SCORE_VALUE = -1;

  private final LinkedList<State> states = new LinkedList<>();

  public FinalFrame() {
    states.add(StateFactory.startPitch());
  }

  @Override
  public FinalFrame play(final int pinCount) {
    if (isGameEnd()) {
      throw new RuntimeException(MSG_ERROR_END_FRAME);
    }

    if (states.getLast().isFinish()) {
      return newPitch(pinCount);
    }

    refreshStates(pinCount);
    return this;
  }

  private FinalFrame newPitch(final int pinCount) {
    states.add(StateFactory.startPitch().nextPitch(pinCount));
    return this;
  }

  private void refreshStates(final int pinCount) {
    State currentState = states.getLast();

    states.removeLast();
    states.add(currentState.nextPitch(pinCount));
  }

  public boolean isGameEnd() {

    if (states.getFirst().isFinish()) {
      return isFinish();
    }
    return false;
  }

  private boolean isFinish() {
    try {
      return score().isFinishBallCount();
    } catch (ScoreCalculateException e) {
      return false;
    }
  }

  @Override
  public String getScoreMessage() {
    return states.stream()
        .map(State::scoreMessage)
        .collect(Collectors.joining(SEPARATOR));
  }

  @Override
  public Score score() {
    Score score = states.getFirst().score();

    for (int i = START_STATES_INDEX; i < states.size(); i++) {
      score = states.get(i).calculateScore(score);
    }

    return score;
  }

  @Override
  public Score frameScoreAdd(Score score) {
    for (State state : states) {
      score = state.calculateScore(score);
    }

    return score;
  }

  @Override
  public int scoreValue() {
    return score().getScoreDto().getScore();
  }

  @Override
  public ScoreResult createScoreResult() {
    if (!isFinish()) {
      return new ScoreResult(getScoreMessage(), NOT_SUM_SCORE_VALUE);
    }

    try {
      return new ScoreResult(getScoreMessage(), scoreValue());
    } catch (ScoreCalculateException e) {
      return new ScoreResult(getScoreMessage(), NOT_SUM_SCORE_VALUE);
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
  }
}