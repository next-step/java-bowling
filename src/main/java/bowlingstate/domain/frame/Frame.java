package bowlingstate.domain.frame;

import bowlingstate.domain.Pins;
import bowlingstate.domain.Score;
import bowlingstate.domain.ScoreSymbol;
import bowlingstate.domain.state.Ready;
import bowlingstate.domain.state.State;
import java.util.List;
import org.springframework.util.ObjectUtils;

public abstract class Frame {

  private static final int NORMAL_FRAME_MAX_SIZE = 2;
  protected final Pins pins;
  protected State state;
  protected Score score;

  public Frame() {
    this.pins = new Pins();
    this.state = new Ready();
  }

  public boolean hasScore() {
    return !ObjectUtils.isEmpty(score);
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }

  protected ScoreSymbol symbol() {
    return ScoreSymbol.symbol(pins.totalHitPin(), pins.size() < NORMAL_FRAME_MAX_SIZE);
  }

  abstract public void initScore();

  abstract public List<String> frameState();

  abstract public void play(int countOfHitPin);

  abstract public boolean isEndFrame();

  abstract protected void validateHitPin(int countOfHitPin);
}
