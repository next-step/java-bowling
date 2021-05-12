package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.result.FrameResult;
import bowling.domain.result.TotalResult;
import bowling.domain.state.State;
import bowling.domain.state.finished.SpecialShot;
import bowling.domain.state.running.Ready;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotCalculateException;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
  private static final String WALL = "|";

  private final LinkedList<State> states;

  protected FinalFrame() {
    states = new LinkedList<>();
    states.add(Ready.of());
  }

  @Override
  public Frame bowl(FallenPins fallenPins) {
    State state = states.getLast();

    if (!state.isFinished()) {
      states.removeLast();
      states.add(state.bowl(fallenPins));
      return this;
    }

    if (!checkFinished()) {
      state = SpecialShot.of(fallenPins);
      states.add(state);
    }

    return this;
  }

  @Override
  public Frame bowl(int pins) {
    return bowl(new FallenPins(pins));
  }

  @Override
  public TotalResult showFullResult() {
    throw new RuntimeException();
  }

  @Override
  public void addResult(TotalResult totalResult) {
    boolean scoreVisible = states.getFirst().isFinished();

    FrameResult frameResult;

    try {
      frameResult = new FrameResult(show(), score().getScore(), scoreVisible);
    } catch (CannotCalculateException cannotCalculateException) {
      frameResult = new FrameResult(show(), -1, false);
    }

    totalResult.add(frameResult);
  }

  @Override
  public Score score() {
    Score score = states.getFirst().calculateScore();

    if (score.canCalculateScore()) {
      return score;
    }

    for (int i = 1; i < states.size(); i++) {
      score = states.get(i).addScore(score);
    }
    return score;
  }

  @Override
  public Score calculateAdditionalScore(Score score) {
    if (score.canCalculateScore()) {
      return score;
    }
    Score addingScore = score;
    for (State state : states) {
      addingScore = state.addScore(addingScore);
    }

    return addingScore;
  }

  @Override
  public boolean checkReady() {
    return states.getFirst() instanceof Ready;
  }

  @Override
  public boolean checkFinished() {
    Score score;
    try {
      score = score();
    } catch (CannotCalculateException cannotCalculateException) {
      return false;
    }
    return score.canCalculateScore();
  }

  @Override
  public int round() {
    return 10;
  }

  @Override
  public String show() {
    return states.stream().map(State::show).collect(Collectors.joining(WALL));
  }

}
