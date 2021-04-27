package bowling.view;

import bowling.domain.Bowling;

public class ResultView {

  private static final String DELIMITER = "|";
  private static final String SCORE_FORMAT = "|  %s |%s| %s";
  private static final String BOARD_FORMAT
      = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

  public static void printScoreBoard(Bowling bowling) {
    System.out.println(BOARD_FORMAT);
    String scoreBoard = drawScoreBoard(bowling);
    System.out.printf(SCORE_FORMAT, bowling.getPlayer(), scoreBoard, System.lineSeparator());

  }

  private static String drawScoreBoard(Bowling bowling) {
    return bowling.getFrames().stream()
        .map(frame -> frame.getScoreBoard())
        .reduce((a, b) -> String.join(DELIMITER, a, b))
        .get();
  }

  public static void printBonus(Bowling bowling, int bonus) {
    System.out.println(BOARD_FORMAT + " bonus");
    String scoreBoard = drawScoreBoard(bowling);
    System.out.printf(SCORE_FORMAT, bowling.getPlayer(), scoreBoard, bonus);
  }
}
