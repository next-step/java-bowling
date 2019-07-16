package bowling.view;

import java.util.HashMap;
import java.util.Map;

public class OutputView {

  private static final String FIRST_LINE ="|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
  public static void initialBoardPrint(Map<Integer,String> results) {
    firstLInePrint();
    gameResultPrint(results);
  }

  private static void gameResultPrint(Map<Integer, String> results) {

  }

  private static void firstLInePrint() {
    System.out.println(FIRST_LINE);
  }

}
