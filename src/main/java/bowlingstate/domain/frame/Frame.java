package bowlingstate.domain.frame;

import bowlingstate.domain.Pins;
import bowlingstate.domain.Score;
import java.util.List;
import org.springframework.util.ObjectUtils;

public abstract class Frame {

  protected final Pins pins;
  protected Score score;

  public Frame() {
    this.pins = new Pins();
  }

  public boolean hasScore() {
    return !ObjectUtils.isEmpty(score);
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }

  abstract public void initScore();

  abstract public List<String> frameState();

  abstract public void play(int countOfHitPin);

  abstract public boolean isEndFrame();

  abstract protected void validateHitPin(int countOfHitPin);
}
