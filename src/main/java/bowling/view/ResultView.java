package bowling.view;

import static java.util.stream.Collectors.toList;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import bowling.domain.Frame;
import bowling.domain.Game;

public class ResultView {
    private static final String HEADER =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String HEADER_BONUS =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";
    private static final String SCORE =
            "|  {0} |  {1} |  {2} |  {3} |  {4} |  {5} |  {6} |  {7} |  {8} |  {9} |  {10} |\n";
    private static final int ZERO_INDEX = 0;
    private static final int COLUMN_INTERVAL = 3;
    private static final String BLANK = " ";
    private static final int FIRST_FRAME = 1;
    private static final int LAST_FRAME = 10;
    private static final PrintStream CONSOLE = System.out;

    public static String print(Game param) {
        List<Frame> frames = param.getFrames();
        printHeader(frames);
        List<String> scores = frames.stream()
                                    .map(frame -> display(frame.desc()))
                                    .collect(toList());
        scores.add(ZERO_INDEX, param.getUserName());
        MessageFormat messageFormat = new MessageFormat(SCORE);
        String scoreBoard = messageFormat.format(format(scores));
        CONSOLE.print(scoreBoard);
        CONSOLE.println();
        return scoreBoard;
    }

    private static String display(String desc) {
        StringBuilder builder = new StringBuilder();
        builder.append(desc);
        builder.append(appendBlank(COLUMN_INTERVAL - builder.length()));
        return builder.toString();
    }

    private static String appendBlank(int repeatCount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repeatCount; i++) {
            builder.append(BLANK);
        }
        return builder.toString();
    }

    private static void printHeader(List<Frame> frames) {
        if (frames.size() == LAST_FRAME
            && frames.get(LAST_FRAME - FIRST_FRAME).desc().length() > 3) {
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
