package bowling.state;

import bowling.Pins;
import bowling.Score;

public interface State {

  String RESULT_CONCAT_SYMBOL = "|";

  State bowl(Pins pins);

  boolean isFinish();

  String desc();

  Score getScore();

  Score addAdditionalScore(Score prevScore);

}
