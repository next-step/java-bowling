package bowling.domain;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class FrameScore {

  public static final String SCORE_SEPARATOR = "|";
  private List<String> scores;
  public FrameScore(List<String> scores) {
    this.scores = scores;
  }

  public String getScore() {
    return StringUtils.join(scores, SCORE_SEPARATOR);
  }
}
