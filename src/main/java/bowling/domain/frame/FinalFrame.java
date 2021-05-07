package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.result.FrameResult;
import bowling.domain.result.TotalResult;
import bowling.domain.state.State;
import bowling.domain.state.finished.Spare;
import bowling.domain.state.finished.Strike;
import bowling.domain.state.running.Ready;
import bowling.domain.turn.FallenPins;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {
  private static final String WALL = "|";
  private static final int SHOT_LIMIT = 1;

  private LinkedList<State> states;

  protected FinalFrame(int round) {
    super(round);
    states = new LinkedList<>();
    states.add(Ready.of());
  }

  @Override
  public Frame bowl(FallenPins fallenPins) {
    State state = states.getLast();

    if(!state.isFinished()){
      states.removeLast();
      states.add(state.bowl(fallenPins));
      return this;
    }

    if(checkAddable()){
      state = Ready.of();
      state = state.bowl(fallenPins);
      states.add(state);
    }

    return this;
  }

  @Override
  public TotalResult showFullResult() {
    throw new RuntimeException();
  }

  @Override
  public void addResult(TotalResult totalResult) {
    boolean scoreVisible = states.getFirst().isFinished();

    totalResult.add(new FrameResult(show(), score().getScore(), scoreVisible));
  }

  @Override
  public Score score() {
    Score score = states.getFirst().calculateScore();

    if(score.canCalculateScore()){
      return score;
    }

    return states.getLast().addScore(score);
  }

  @Override
  protected Score calculateAdditionalScore(Score score) {
    if(score.canCalculateScore()){
      return score;
    }

    for(State state: states){
      score = state.addScore(score);
    }

    return score;
  }

  @Override
  public boolean checkFinished() {
    State head = states.getFirst();

    if(!head.isFinished()){
      return false;
    }

    if(head.isFinished()){
      return !checkAddable();
    }

    return true;
  }

  private boolean checkAddable(){
    State head = states.getFirst();
    if(states.size()==SHOT_LIMIT && (head instanceof Spare || head instanceof Strike)){
      return true;
    }
    return false;
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
