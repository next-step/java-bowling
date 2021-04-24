package bowling.domain;

import java.util.List;
import org.springframework.util.ObjectUtils;

public abstract class Frame {

  private static final int NORMAL_FRAME_MAX_SIZE = 2;
  private static final int TOTAL_NORMAL_FRAME = 9;
  protected final Pins pins;
  protected Score score;

  public Frame() {
    pins = new Pins();
  }

  public void play(int countOfHitPin) {
    validateHitPin(countOfHitPin);
    pins.add(new Pin(countOfHitPin));
  }

  public void initScore() {
    this.score = Score.of(symbol(), pins);
  }

  public boolean hasScore() {
    return !ObjectUtils.isEmpty(score);
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }

  public List<String> frameState() {
    return pins.frameState();
  }

  protected ScoreSymbol symbol() {
    return ScoreSymbol.symbol(pins.totalHitPin(), pins.size() < NORMAL_FRAME_MAX_SIZE);
  }

  abstract public boolean isEndFrame();

  abstract public boolean isLastFrame();

  abstract protected void validateHitPin(int countOfHitPin);
}
