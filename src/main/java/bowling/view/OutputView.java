package bowling.view;

import bowling.BowlingGameResult;
import bowling.Player;
import bowling.util.TextArrangeUtil;

public class OutputView {

  private static final String FIRST_LINE = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";

  public static void initialBoardPrint(Player player, BowlingGameResult results) {
    firstLInePrint();
    System.out.println(gameResultPrint(results, player));
  }

  private static String gameResultPrint(BowlingGameResult results, Player player) {
    StringBuffer resultView = new StringBuffer();
    resultView.append("|");
    resultView.append(TextArrangeUtil.arrange(player.toString(), 8));
    resultView.append("|");
    for (int i = 1; i < 11; i++) {
      resultView.append(TextArrangeUtil.arrange(results.hasResult(i) ? results.result(i) : "", 8));
      resultView.append("|");
    }
    return resultView.toString();
  }

  private static void firstLInePrint() {
    System.out.println(FIRST_LINE);
  }

}
