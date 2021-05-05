package bowling.domain.state;

import bowling.domain.turn.FallenPins;

public interface State {
  boolean isFinished();
  State bowl(FallenPins pins);
}
