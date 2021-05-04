package bowling.domain;

import bowling.utils.StringUtils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameScore {

  public static final String SCORE_SEPARATOR = "|";
  public static final String STRIKE = "X";
  public static final String GUTTER = "-";
  public static final String SPARE = "/";

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
      return STRIKE;
    }
    if (score == Pins.MIN) {
      return GUTTER;
    }
    if (scores.size() > 1 && isClear(index)) {
      return SPARE;
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
