package bowling.view;

import bowling.domain.Bowling;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class ResultView {

  private static final String DELIMITER = "|";
  private static final String SCORE_FORMAT = "|  %s |%s  | %n";
  private static final String BOARD_FORMAT
      = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";
  private static final String BLANK = "      ";
  private static final String FRAME_FORMAT = "  %-4s";
  private static final String FINAL_FRAME_FORMAT = " %-5s";

  public void printScoreBoard(Bowling bowling) {
    System.out.println(BOARD_FORMAT);
    String scoreBoard = drawScoreBoard(bowling.getScores());
    System.out.printf(SCORE_FORMAT, bowling.getPlayerName(), scoreBoard);
  }

  private static String drawScoreBoard(List<String> results) {
    List<String> scores = results.stream()
        .map(result -> getScore(result))
        .collect(Collectors.toList());
    return StringUtils.join(scores, DELIMITER);
  }

  private static String getScore(String result) {
    if (StringUtils.isBlank(result)) {
      return BLANK;
    }
    if (result.length() > 4) {
      return String.format(FINAL_FRAME_FORMAT, result);
    }
    return String.format(FRAME_FORMAT, result);
  }

}
