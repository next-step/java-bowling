package bowling.view;

import bowling.GameResult;
import bowling.Player;
import bowling.util.TextArrangeUtil;

public class OutputView {

  private static final String FIRST_LINE = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";

  public static void initialBoardPrint(Player player, GameResult results) {
    firstLInePrint();
    System.out.println(gameResultPrint(results, player));
    System.out.println(gameScorePrint(results, player));
  }

  private static String gameResultPrint(GameResult results, Player player) {
    StringBuffer resultView = new StringBuffer();
    resultView.append("|");
    resultView.append(TextArrangeUtil.arrange(player.toString(), 8));
    resultView.append("|");
    for (int i = 1; i < 11; i++) {
      resultView.append(TextArrangeUtil.arrange(results.frameResult(i), 8));
      resultView.append("|");
    }
    return resultView.toString();
  }

  private static String gameScorePrint(GameResult results, Player player) {
    StringBuffer resultView = new StringBuffer();
    resultView.append("|");
    resultView.append(TextArrangeUtil.arrange(player.toString(), 8));
    resultView.append("|");
    int score = 0;
    for (int i = 1; i < 11; i++) {
      resultView.append(TextArrangeUtil
          .arrange(results.scoreResult(i) != -1 ? (score += results.scoreResult(i)) + "" : "", 8));
      resultView.append("|");
    }
    return resultView.toString();
  }

  private static void firstLInePrint() {
    System.out.println(FIRST_LINE);
  }

}
