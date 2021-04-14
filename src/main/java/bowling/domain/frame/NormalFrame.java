package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame extends Frame {


  public NormalFrame(int playCount, State state) {
    super(playCount, state);
  }

  public static NormalFrame createFirst() {
    return new NormalFrame(0, new Ready());
  }

}
