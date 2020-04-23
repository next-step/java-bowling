package bowling.view;

import static java.util.stream.Collectors.toList;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import bowling.domain.Frame;
import bowling.domain.FrameResult;
import bowling.domain.Game;

public class ResultView {
    private static final String HEADER =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String HEADER_BONUS =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";
    private static final String DISPLAY =
            "|  {0} |  {1} |  {2} |  {3} |  {4} |  {5} |  {6} |  {7} |  {8} |  {9} |  {10} |\n";
    private static final String SCORE =
            "|      |  {0} |  {1} |  {2} |  {3} |  {4} |  {5} |  {6} |  {7} |  {8} |  {9} |\n";

    private static final int ZERO_INDEX = 0;
    private static final int COLUMN_INTERVAL = 3;
    private static final String BLANK = " ";
    private static final int FIRST_FRAME = 1;
    private static final int LAST_FRAME = 10;
    private static boolean bonusHeaderFlag = false;
    private static final PrintStream CONSOLE = System.out;

    public static String print(Game game) {
        bonusHeaderFlag = false;
        List<FrameResult> frameResult = game.getFrameResult();
        printHeader(frameResult);
        String display = printDisplayScore(game, frameResult);
        String score = printTotalScore(frameResult);
        return display + score;
    }

    private static String printTotalScore(List<FrameResult> frameResult) {
        List<String> totalScore = frameResult.stream()
                                             .filter(frame -> frame.getTotalScore() != -1)
                                             .map(frame -> displayText(frame, true))
                                             .collect(toList());
        if (bonusHeaderFlag) {
            String lastScore = totalScore.get(LAST_FRAME - 1) + appendBlank(2);
            totalScore.add(LAST_FRAME - 1, lastScore);
        }
        MessageFormat messageFormat = new MessageFormat(SCORE);
        String score = messageFormat.format(format(totalScore));
        CONSOLE.print(score);
        return score;
    }

    private static String printDisplayScore(Game game, List<FrameResult> frameResult) {
        List<String> displayScore = frameResult.stream()
                                               .filter(frame -> !frame.getDisplayScore().isEmpty())
                                               .map(frame -> displayText(frame, false))
                                               .collect(toList());
        displayScore.add(ZERO_INDEX, game.getUserName());
        MessageFormat messageFormat = new MessageFormat(DISPLAY);
        String display = messageFormat.format(format(displayScore));
        CONSOLE.print(display);
        return display;
    }

    private static String displayText(FrameResult frameResult, boolean totalScoreFlag) {
        StringBuilder builder = new StringBuilder();
        String display = getDisplayText(frameResult, totalScoreFlag);
        builder.append(display);
        builder.append(appendBlank(COLUMN_INTERVAL - builder.length()));
        return builder.toString();
    }

    private static String getDisplayText(FrameResult frameResult, boolean totalScoreFlag) {
        String display = frameResult.getDisplayScore();
        if (totalScoreFlag) {
            display = String.valueOf(frameResult.getTotalScore());
        }
        return display;
    }

    private static String appendBlank(int repeatCount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repeatCount; i++) {
            builder.append(BLANK);
        }
        return builder.toString();
    }

    private static void printHeader(List<FrameResult> frames) {
        if (frames.size() == LAST_FRAME
            && frames.get(LAST_FRAME - FIRST_FRAME).getDisplayScore().length() > 3) {
            bonusHeaderFlag = true;
            CONSOLE.println(HEADER_BONUS);
            return;
        }
        CONSOLE.println(HEADER);
    }

    private static Object[] format(List<String> scores) {
        for (int i = FIRST_FRAME; i <= LAST_FRAME; i++) {
            if (scores.size() <= i) {
                scores.add(appendBlank(3));
            }
        }
        return scores.toArray();
    }
}
