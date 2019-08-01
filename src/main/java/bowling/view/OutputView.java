package bowling.view;

import bowling.GameResult;
import bowling.util.TextArrangeUtil;
import java.util.List;

public class OutputView {

  private static final String FIRST_LINE = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";

  private static void firstLInePrint() {
    System.out.println(FIRST_LINE);
  }

  public static void initialBoardPrint(List<GameResult> results) {
    firstLInePrint();
    for (GameResult gameResult : results) {
      System.out.println(gameResultPrint(gameResult));
      System.out.println(gameScorePrint(gameResult));
    }
  }

  private static String gameResultPrint(GameResult result) {
    StringBuffer resultView = new StringBuffer();
    resultView.append("|");
    resultView.append(TextArrangeUtil.arrange(result.getPlayerName(), 8));
    resultView.append("|");
    for (int i = 1; i < 11; i++) {
      resultView.append(TextArrangeUtil.arrange(result.frameResult(i), 8));
      resultView.append("|");
    }
    return resultView.toString();
  }

  private static String gameScorePrint(GameResult results) {
    StringBuffer resultView = new StringBuffer();
    resultView.append("|");
    resultView.append(TextArrangeUtil.arrange("", 8));
    resultView.append("|");
    int score = 0;
    for (int i = 1; i < 11; i++) {
      resultView.append(TextArrangeUtil
          .arrange(results.scoreResult(i) != -1 ? (score += results.scoreResult(i)) + "" : "", 8));
      resultView.append("|");
    }
    return resultView.toString();
  }
}
