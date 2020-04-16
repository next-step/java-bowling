package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Score;

import java.util.List;
import java.util.stream.Collectors;

import static bowling.view.FrameFormat.*;

public class ResultView {
    private static final int ONE = 1;

    public static void printBowlingFrame(Player player) {
        System.out.println(BOWLING_FRAME.getForamt());
        System.out.println(String.format(DEFAULT_SCORE_FRAME.getForamt(), player.getName()));
        System.out.println(EMPTY_FRAME.getForamt());
    }

    public static void printBowlingScore(Frames frames, Player player) {
        System.out.println(BOWLING_FRAME.getForamt());
        StringBuilder result = new StringBuilder(String.format(NAME_FORMAT.getForamt(), player.getName()));
        StringBuilder pointResult = new StringBuilder(BLANK_FRAME.getForamt());

        for (int i = 0, end = frames.size(); i < end; i++) {
            result.append(formatScores(frames.get(i)));
            pointResult.append(formatPoint(frames, i));
        }

        System.out.println(addBlankFrame(frames.size(), result));
        System.out.println(addBlankFrame(frames.size(), pointResult));
    }

    private static String formatScores(Frame frame) {
        List<String> scores = frame.getScores().stream()
                .map(Score::pointToScore)
                .collect(Collectors.toList());

        if (scores.size() > ONE) {
            return String.format(MULTI_SCORE_FORMAT.getForamt(), String.join(FRAME_LINE.getForamt(), scores));
        }
        return String.format(SINGLE_SCORE_FORMAT.getForamt(), scores.get(0));
    }

    private static String formatPoint(Frames frames, int frameIndex) {
        return String.format(POINT_SCORE_FORMAT.getForamt(), frames.calculateFramePoint(frameIndex));
    }

    private static StringBuilder addBlankFrame(int frameSize, StringBuilder result) {
        for (int i = 0; i < 10 - frameSize; i++) {
            result.append(BLANK_FRAME_FORMAT.getForamt());
        }
        return result;
    }
}
