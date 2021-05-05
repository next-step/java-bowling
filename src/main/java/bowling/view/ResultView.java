package bowling.view;

import bowling.domain.Result;
import bowling.domain.frame.Frame;
import bowling.domain.frame.PlayerBoard;
import bowling.domain.turn.FallenPins;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
  private static final String SCORE_TITLE = "| NAME | 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08 | 09 | 10 |";
  private static final String WALL = "|";
  private static final String BLANK = " ";

  private static final String EMPTY_ROUND = "    ";
  private static final String NAME_FORMAT = "%6s";
  private static final String NUMBER_FORMAT = "%4s";

  private static final int ZERO = 0;
  private static final int STRIKE_SHOT = 10;
  private static final int MAX_SHOT_PER_FRAME = 2;

  public void printBoard(PlayerBoard playerBoard) {
    System.out.println();
    System.out.println(SCORE_TITLE);

    printName(playerBoard.playerName());
    Result playerResult = playerBoard.firstFrame().showFullResult();
    int remainRounds = 10;
    for (String frame : playerResult.frameResults()) {
      printFrameResult(frame);
      remainRounds--;
    }
    while (remainRounds-- > 0) {
      System.out.print(EMPTY_ROUND);
      System.out.print(WALL);
    }
    System.out.println();
  }

  private void printName(String name) {
    System.out.print(String.format(NAME_FORMAT, name));
    System.out.print(BLANK + WALL);
  }

  private void printFrameResult(String frameResult) {


    System.out.print(String.format(NUMBER_FORMAT, frameResult));
    System.out.print(WALL);
  }

}
