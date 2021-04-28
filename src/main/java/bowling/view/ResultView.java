package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Frame;
import bowling.domain.Result;

public class ResultView {

  private static final String DELIMITER = "|";
  private static final String SCORE_FORMAT = "|  %s |%s| %s";
  private static final String BOARD_FORMAT
      = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private static final String BLANK = "      ";

  public static void printScoreBoard(Bowling bowling) {
    System.out.println(BOARD_FORMAT);
    String scoreBoard = drawScoreBoard(bowling);
    System.out.printf(SCORE_FORMAT, bowling.getPlayer(), scoreBoard, System.lineSeparator());

  }

  private static String drawScoreBoard(Bowling bowling) {
    return bowling.getFrames().stream()
        .map(ResultView::getScoreBoard)
        .reduce((a, b) -> String.join(DELIMITER, a, b))
        .get();
  }

  private static  String getScoreBoard(Frame frame) {
    if (frame.getFirst() == null) {
      return BLANK;
    }

    String firstScoreString = getFirstScoreString(frame);
    if (frame.getSecond() == null) {
      return String.format("  %s   ", firstScoreString);
    }

    String secondScoreString = getSecondScoreString(frame);
    return String.format("  %s|%s ", firstScoreString, secondScoreString);
  }

  private static String getFirstScoreString(Frame frame) {
    Result result = frame.getFirst().getResult();
    return result.isNotMiss() ? result.getMark() : String.valueOf(frame.getFirstHit());
  }

  private static String getSecondScoreString(Frame frame) {
    Result result = frame.getSecond().getResult();
    return result.isNotMiss() ? result.getMark() : String.valueOf(frame.getSecondHit());
  }
}
