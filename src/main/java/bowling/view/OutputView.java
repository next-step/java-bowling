package bowling.view;

import bowling.domain.dto.ScoreResultDto;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ROUND_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DEFAULT_SCORE = "|  %s |      |      |      |      |      |      |      |      |      |      |";
    private static final String DEFAULT_TOTAL_SCORE = "|      |      |      |      |      |      |      |      |      |      |      |";
    private static final String LINE = "|";
    private static final String NAME = "  %s |";
    private static final String BLANK = "      |";
    private static final String MULTI_SCORE_FORMAT = "  %s |";
    private static final String SINGLE_SCORE_FORMAT = "  %s   |";
    private static final String POINT_FORMAT = " %4s |";

    public static void outputDefaultFrame(String playerName) {
        System.out.println(ROUND_FRAME);
        System.out.println(String.format(DEFAULT_SCORE, playerName));
        System.out.println(DEFAULT_TOTAL_SCORE);
    }

    public static void outputFrames(Frames frames, Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = outputRound(stringBuilder);
        stringBuilder = outputNameAndScore(stringBuilder, frames, player);
        stringBuilder = outputTotalScore(stringBuilder, frames);
        System.out.println(stringBuilder);
    }

    private static StringBuilder outputRound(StringBuilder stringBuilder) {
        stringBuilder.append(ROUND_FRAME);
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    private static StringBuilder outputNameAndScore(StringBuilder stringBuilder, Frames frames, Player player) {
        stringBuilder.append(LINE);
        stringBuilder.append(String.format(NAME, player.getName()));

        for (Frame frame : frames.getFrames()) {
            stringBuilder.append(converterFrame(frame));
        }

        stringBuilder = appendBlank(stringBuilder, frames.getFrameSize());
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    private static String converterFrame(Frame frame) {
        List<String> scoresString = frame.getScoreResultDtos()
                .stream()
                .map(ScoreResultDto::scoreToString)
                .collect(Collectors.toList());

        if (scoresString.size() > 1) {
            return String.format(MULTI_SCORE_FORMAT, String.join(LINE, scoresString));
        }
        return String.format(SINGLE_SCORE_FORMAT, scoresString.get(0));
    }

    private static StringBuilder appendBlank(StringBuilder stringBuilder, int frameSize) {
        for (int i = 0; i < 10 - frameSize; i++) {
            stringBuilder.append(BLANK);
        }

        return stringBuilder;
    }

    private static StringBuilder outputTotalScore(StringBuilder stringBuilder, Frames frames) {
        stringBuilder.append(LINE);
        stringBuilder.append(BLANK);

        Integer totalScore = 0;
        int i = 0;
        List<Frame> frame = frames.getFrames();
        for (; i < frames.getFrameSize(); i++) {
            if (!frame.get(i).isAvailableCalculatePoint()) {
                break;
            }
            totalScore = appendTotalCount(stringBuilder, frame.get(i), totalScore);
        }

        stringBuilder = appendBlank(stringBuilder, i);
        return stringBuilder;
    }

    private static Integer appendTotalCount(StringBuilder stringBuilder, Frame frame, Integer totalScore) {
        stringBuilder.append(String.format(POINT_FORMAT, totalScore + frame.totalScore()));
        return totalScore + frame.totalScore();
    }
}
