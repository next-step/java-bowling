package bowling.domain.frame;


import bowling.domain.state.Ready;
import bowling.domain.state.State;
import java.util.Objects;

public class NormalFrame {

  private final int playCount;
  private final State state;

  public NormalFrame(int playCount, State state) {
    this.playCount = playCount;
    this.state = state;
  }

  public static NormalFrame createFirst() {
    return new NormalFrame(1, new Ready());
  }

  public void play(int bawlingCount) {
    validateBawlingCount(bawlingCount);
  }

  private void validateBawlingCount(int bawlingCount) {
    if (bawlingCount < 1 || bawlingCount > 10) {
      throw new IllegalArgumentException();
    }
  }

}
