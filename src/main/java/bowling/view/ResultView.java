package bowling.view;

import bowling.domain.Frame;

import java.util.List;

public class ResultView {

    private static final String BOWLING_SCORE_TOP_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_SEPERATE_LINE = "|";
    private static final String SPACE = " ";

    public static void printScore(String user, Frame frame, List<Frame> frames) {
        System.out.println(BOWLING_SCORE_TOP_FORMAT);

        StringBuilder format = new StringBuilder(SCORE_SEPERATE_LINE);
        format.append(String.format("%5s", user)).append(SPACE);
        format.append(SCORE_SEPERATE_LINE);

        for (Frame frame1 : frames) {
            format.append(frame1.getScoreFormat());
            format.append(SCORE_SEPERATE_LINE);
        }
        format.append(frame.getScoreFormat());
        format.append(SCORE_SEPERATE_LINE);

        format.append(makeEmptyScoreFormat(frames.size()));

        System.out.println(format);

    }

    private static String makeEmptyScoreFormat(int size) {
        int count = 9 - size;
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < count; i++) {
            format.append("      |");
        }
        return format.toString();
    }

}
