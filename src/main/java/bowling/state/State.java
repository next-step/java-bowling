package bowling.state;

import bowling.Pins;

public interface State {

  String RESULT_CONCAT_SYMBOL ="|";

  State bowl(Pins pins);

  boolean isFinish();

  String desc();
}
