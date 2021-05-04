package bowling.domain;

import bowling.utils.StringUtils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameScore {

  public static final String SCORE_SEPARATOR = "|";

  private List<Integer> scores;

  public FrameScore(List<Integer> scores) {
    this.scores = scores;
  }

  public String getScore() {
    List<String> scoreStrings = IntStream.range(0, scores.size())
        .mapToObj(index -> getScoreString(index))
        .collect(Collectors.toList());
    return StringUtils.join(scoreStrings, SCORE_SEPARATOR);
  }

  public String getScoreString(int index) {
    Integer score = scores.get(index);
    if (score == Pins.MAX) {
      return Result.STRIKE.mark();
    }
    if (score == Pins.MIN) {
      return Result.GUTTER.mark();
    }
    if (scores.size() > 1 && isClear(index)) {
      return Result.SPARE.mark();
    }
    return String.valueOf(score);
  }

  private boolean isClear(int index) {
    if (index > 0) {
      return scores.get(index) + scores.get(index - 1) == Pins.MAX;
    }
    return false;
  }
}
