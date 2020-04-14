package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String BOWLING_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BLANK_FRAME_FORMAT = "      |";
    private static final String BLANK_FRAME = "|      |";
    private static final String FRAME_LINE = "|";
    private static final String DEFAULT_SCORE_FRAME = "|  %s |      |      |      |      |      |      |      |      |      |";
    private static final String EMPTY_FRAME = "|      |      |      |      |      |      |      |      |      |      |";
    private static final String NAME_FORMAT = "  %s |";
    private static final String MULTI_SCORE_FORMAT = "  %s |";
    private static final String SINGLE_SCORE_FORMAT = "  %s   |";


    public static void printBowlingFrame(Player player) {
        System.out.println(BOWLING_FRAME);
        System.out.println(String.format(DEFAULT_SCORE_FRAME, player.getName()));
        System.out.println(EMPTY_FRAME);
    }

    public static void printBowlingScore(Frames frames) {
        System.out.println(BOWLING_FRAME);
        Player player = frames.getPlayer();

        StringBuilder result = new StringBuilder(FRAME_LINE);
        StringBuilder pointResult = new StringBuilder(BLANK_FRAME);

        result.append(String.format(NAME_FORMAT, player.getName()));

        for (int i = 0, end = frames.size(); i < end; i++) {
            result.append(formatScores(frames.get(i)));
            pointResult.append(formatPoint(frames.get(i), i));
        }

        addBlankFrame(frames, result);
        addBlankFrame(frames, pointResult);
        System.out.println(result);
        System.out.println(pointResult);
    }

    private static String formatScores(Frame frame) {
        List<String> scores = frame.getScores().stream()
                .map(Score::pointToScore)
                .collect(Collectors.toList());

        if (scores.size() > 1) {
            return String.format(MULTI_SCORE_FORMAT, String.join(FRAME_LINE, scores));
        }
        return String.format(SINGLE_SCORE_FORMAT, scores.get(0));
    }

    private static String formatPoint(Frame frame, int frameIndex) {
        return String.format(MULTI_SCORE_FORMAT, frame.getTotalPoint(frameIndex));
    }

    private static void addBlankFrame(Frames frames, StringBuilder result) {
        for (int i = 0, frameSize = frames.size(); i < 10 - frameSize; i++) {
            result.append(BLANK_FRAME_FORMAT);
        }
    }
}
