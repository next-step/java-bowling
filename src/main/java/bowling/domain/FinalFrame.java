package bowling.domain;

import bowling.domain.state.Final;

public class FinalFrame extends Frame {

  public FinalFrame() {
    super(10);
  }

  @Override
  public FinalFrame roll(int pins) {
    if (state == null) {  // first
      state = new Final();
      state.roll(pins);

      return this;
    }

    state.roll(pins);
    return this;
  }

  @Override
  public Score score(Score score) {
    return state.score(score);
  }

  @Override
  public Score accumulate(Score score) {
    System.out.println("        " + number + ", accumulate score: " + score);

    return state.accumulate(score);
  }

  @Override
  public String toString() {
    return "FinalFrame{" +
        "number=" + number +
        ", state=" + state +
        '}';
  }
}
