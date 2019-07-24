package bowling.view;

import bowling.Player;
import bowling.util.TextArrangeUtil;
import java.util.Map;

public class OutputView {

  private static final String FIRST_LINE = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";

  public static void initialBoardPrint(Player player, Map<Integer, String> results) {
    firstLInePrint();
    System.out.println(gameResultPrint(results, player));
  }

  private static String gameResultPrint(Map<Integer, String> results, Player player) {
    StringBuffer resultView = new StringBuffer();
    resultView.append("|");
    resultView.append(TextArrangeUtil.arrange(player.toString(), 8));
    resultView.append("|");
    for (int i = 1; i < 11; i++) {
      resultView.append(TextArrangeUtil.arrange(results.containsKey(i) ? results.get(i) : "", 8));
      resultView.append("|");
    }
    return resultView.toString();
  }

  private static void firstLInePrint() {
    System.out.println(FIRST_LINE);
  }

}
