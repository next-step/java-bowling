package bowling.view;

import bowling.domain.frame.PlayerBoard;
import bowling.domain.result.FrameResult;
import bowling.domain.result.TotalResult;

import java.util.List;

public class ResultView {
  private static final String SCORE_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private static final String WALL = "|";
  private static final String BLANK = " ";

  private static final String EMPTY_ROUND = "     ";
  private static final String NAME_FORMAT = "%5s";
  private static final String NUMBER_FORMAT = "%6s";

  public void printFullBoard(List<PlayerBoard> playerBoards) {
    for(PlayerBoard playerBoard : playerBoards){
      printBoard(playerBoard);
    }
  }

  private void printBoard(PlayerBoard playerBoard) {
    System.out.println(SCORE_TITLE);

    printName(playerBoard.playerName());
    TotalResult playerTotalResult = playerBoard.firstFrame().showFullResult();
    int remainRounds = 10;
    for (FrameResult frame : playerTotalResult.frameResults()) {
      printFrameResult(frame.frameView());
      remainRounds--;
    }
    printEmptyFields(remainRounds);

    printName(EMPTY_ROUND);
    for (FrameResult frame : playerTotalResult.frameResults()) {
      printFrameResult(frame.score());
    }

    printEmptyFields(remainRounds);
  }

  private void printName(String name) {
    System.out.print(WALL + String.format(NAME_FORMAT, name));
    System.out.print(BLANK + WALL);
  }

  private void printFrameResult(String frameResult) {

    System.out.print(String.format(NUMBER_FORMAT, frameResult));
    System.out.print(WALL);
  }

  private void printEmptyFields(int remainFields) {
    while (remainFields-- > 0) {
      System.out.print(String.format(NUMBER_FORMAT, EMPTY_ROUND));
      System.out.print(WALL);
    }
    System.out.println();
  }


}
