package bowling.domain;

import bowling.domain.state.State;
import bowling.utils.StringUtils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameScore {

  public static final String SCORE_SEPARATOR = "|";

  private List<State> states;

  public FrameScore(List<Integer> scores) {
    this.states = IntStream.range(0, scores.size())
        .mapToObj(index -> parseScoreToState(scores, index))
        .collect(Collectors.toList());
  }

  public State parseScoreToState(List<Integer> scores, int index) {
    Integer score = scores.get(index);
    if (score == Pins.MIN) {
      return State.gutter();
    }
    if (index > 0 && isClear(scores, index)) {
      return State.spare(score);
    }
    if (score == Pins.MAX) {
      return State.strike();
    }
    return State.miss(score);
  }

  public String getScore() {
    List<String> scores = states.stream().map(State::getScore).collect(Collectors.toList());
    return StringUtils.join(scores, SCORE_SEPARATOR);
  }

  private boolean isClear(List<Integer> scores, int index) {
    if (index > 0) {
      return scores.get(index) + scores.get(index - 1) == Pins.MAX;
    }
    return false;
  }
}
