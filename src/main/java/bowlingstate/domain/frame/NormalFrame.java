package bowlingstate.domain.frame;

import bowlingstate.domain.Pin;
import bowlingstate.domain.ScoreSymbol;
import bowlingstate.domain.state.Ready;
import bowlingstate.domain.state.State;
import java.util.List;

public class NormalFrame extends Frame {

  private static final int NORMAL_FRAME_MAX_SIZE = 2;
  private static final int MAX_PIN_COUNT = 10;
  private State state;

  public NormalFrame() {
    this.state = new Ready();
  }

  @Override
  public void initScore() {
    this.score = state.of();
  }

  protected void validateHitPin(int countOfHitPin) {
    if (pins.totalHitPin() + countOfHitPin > MAX_PIN_COUNT) {
      throw new IllegalArgumentException("핀의 갯수는 " + MAX_PIN_COUNT + "개를 넘을 수 없습니다.");
    }
  }

  private boolean isStrike() {
    return symbol() == ScoreSymbol.STRIKE;
  }

  public void play(int countOfHitPin) {
    validateHitPin(countOfHitPin);
    pins.add(new Pin(countOfHitPin));
    state = state.bowl(countOfHitPin);
  }

  public List<String> frameState() {
    return state.state();
  }

  public boolean isEndFrame() {
    return pins.size() >= NORMAL_FRAME_MAX_SIZE || isStrike();
  }
}
