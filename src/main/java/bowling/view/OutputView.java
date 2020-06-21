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
    private static final String LINE = "|";
    private static final String NAME = "  %s |";
    private static final String BLANK = "      |";
    private static final String MULTI_SCORE_FORMAT = "  %s |";
    private static final String SINGLE_SCORE_FORMAT = "  %s   |";

    public static void outputDefaultFrame(String playerName) {
        System.out.println(ROUND_FRAME);
        System.out.println(String.format(DEFAULT_SCORE, playerName));
    }

    public static void outputFrames(Frames frames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = outputRound(stringBuilder);
        stringBuilder = outputNameAndScore(stringBuilder, frames);
        System.out.println(stringBuilder);
    }

    private static StringBuilder outputRound(StringBuilder stringBuilder) {
        stringBuilder.append(ROUND_FRAME);
        stringBuilder.append(System.lineSeparator());
        return stringBuilder;
    }

    private static StringBuilder outputNameAndScore(StringBuilder stringBuilder, Frames frames) {
        stringBuilder.append(LINE);
        Player player = frames.getPlayer();
        stringBuilder.append(String.format(NAME, player.getName()));

        for (Frame frame : frames.getFrames()) {
            stringBuilder.append(converterFrame(frame));
        }

        stringBuilder = appendBlank(stringBuilder, frames);
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

    private static StringBuilder appendBlank(StringBuilder stringBuilder, Frames frames) {
        int frameSize = frames.getFrameSize();
        for (int i = 0; i < 10 - frameSize; i++) {
            stringBuilder.append(BLANK);
        }

        return stringBuilder;
    }
}
