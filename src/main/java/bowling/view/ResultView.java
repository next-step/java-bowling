package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ResultView {
    private static final String BOWLING_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DEFAULT_SCORE_FRAME = "|  %s |      |      |      |      |      |      |      |      |      |";
    private static final String FRAME_LINE = "|";
    private static final String NAME_FORMAT = "  %s |";
    private static final String MULTI_SCORE_FORMAT = "  %s |";
    private static final String SINGLE_SCORE_FORMAT = "  %s   |";
    private static final String BLANK_FRAME_FORMAT = "      |";
    private static final int FIRST_SCORE_INDEX = 0;
    private static final int MULTI_FORMAT_NUMBER = 1;
    private static final int LIMIT_FRAMES_SIZE = 10;

    private ResultView() {}

    public static void printBowlingFrame(Player player) {
        System.out.println(BOWLING_FRAME);
        System.out.println(String.format(DEFAULT_SCORE_FRAME, player.getName()));
    }

    public static void printBowlingScore(Player player, Frames frames) {
        System.out.println(BOWLING_FRAME);

        StringBuilder result = new StringBuilder(FRAME_LINE);
        result.append(String.format(NAME_FORMAT, player.getName()));

        for (Frame frame : frames.getFrames()) {
            result.append(formatScores(frame));
        }

        addBlankFrame(frames, result);
        System.out.println(result);
    }

    private static String formatScores(Frame frame) {
        List<String> scores = new ArrayList<>();
        for (int index = FIRST_SCORE_INDEX; index < frame.scoreSize(); index++) {
            scores.add(frame.getScore(index));
        }

        if (scores.size() > MULTI_FORMAT_NUMBER) {
            return String.format(MULTI_SCORE_FORMAT, String.join(FRAME_LINE, scores));
        }
        return String.format(SINGLE_SCORE_FORMAT, scores.get(FIRST_SCORE_INDEX));
    }

    private static void addBlankFrame(Frames frames, StringBuilder result) {
        for (int i = FIRST_SCORE_INDEX; i < LIMIT_FRAMES_SIZE - frames.size(); i++) {
            result.append(BLANK_FRAME_FORMAT);
        }
    }
}
