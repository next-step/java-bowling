package bowling.state;

import bowling.pin.Pin;
import bowling.score.Score;

public interface State {

  State nextPitch(int fallenPin);

  boolean isFinish();

  String scoreMessage();

  Pin totalPin();

  Score score();
}