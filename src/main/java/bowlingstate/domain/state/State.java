package bowlingstate.domain.state;

import bowlingstate.domain.Score;
import java.util.List;

public interface State {

  State bowl(int countOfHitPin);

  boolean isFinished();

  List<String> state();

  Score of();
}
