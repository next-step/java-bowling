package bowling.view;

import bowling.domain.result.FrameResult;
import bowling.domain.result.TotalResult;
import bowling.domain.frame.PlayerBoard;

public class ResultView {
  private static final String SCORE_TITLE = "| NAME | 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08 | 09 | 10 |";
  private static final String WALL = "|";
  private static final String BLANK = " ";

  private static final String EMPTY_ROUND = "    ";
  private static final String NAME_FORMAT = "%5s";
  private static final String NUMBER_FORMAT = "%4s";

  public void printBoard(PlayerBoard playerBoard) {
    System.out.println();
    System.out.println(SCORE_TITLE);

    printName(playerBoard.playerName());
    TotalResult playerTotalResult = playerBoard.firstFrame().showFullResult();
    int remainRounds = 10;
    for (FrameResult frame : playerTotalResult.frameResults()) {
      printFrameResult(frame.frameView());
      remainRounds--;
    }
    while (remainRounds-- > 0) {
      System.out.print(EMPTY_ROUND);
      System.out.print(WALL);
    }
    System.out.println();
  }

  private void printName(String name) {
    System.out.print(WALL + String.format(NAME_FORMAT, name));
    System.out.print(BLANK + WALL);
  }

  private void printFrameResult(String frameResult) {

    System.out.print(String.format(NUMBER_FORMAT, frameResult));
    System.out.print(WALL);
  }

}
