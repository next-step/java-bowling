package bowling.state;

import bowling.pin.Pin;

public interface State {

  State nextPitch(int fallenPin);

  boolean isFinish();

  String score();

  Pin totalPin();
}