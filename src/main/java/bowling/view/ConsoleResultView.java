package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Pitching;
import bowling.domain.frame.Frames;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleResultView implements ResultView {
    private static final String DELIMITER = "|";
    private static final int CELL_WIDTH = 6;
    private static final String NAME_LABEL = "NAME";
    private static final String FRAME_INDEX_FORMAT = "%02d";
    private final Map<Pitching, String> stringByPitching;

    public ConsoleResultView() {
        stringByPitching = new EnumMap<>(Pitching.class);
        stringByPitching.put(Pitching.GUTTER, "-");
        stringByPitching.put(Pitching.ONE_PIN, "1");
        stringByPitching.put(Pitching.TWO_PINS, "2");
        stringByPitching.put(Pitching.THREE_PINS, "3");
        stringByPitching.put(Pitching.FOUR_PINS, "4");
        stringByPitching.put(Pitching.FIVE_PINS, "5");
        stringByPitching.put(Pitching.SIX_PINS, "6");
        stringByPitching.put(Pitching.SEVEN_PINS, "7");
        stringByPitching.put(Pitching.EIGHT_PINS, "8");
        stringByPitching.put(Pitching.NINE_PINS, "9");
        stringByPitching.put(Pitching.STRIKE, "X");
        stringByPitching.put(Pitching.SPARE, "/");
    }

    @Override
    public void print(BowlingGame bowlingGame) {
        StringBuilder resultBuilder = new StringBuilder();
        appendHeader(resultBuilder);
        appendBody(bowlingGame, resultBuilder);
        System.out.println(resultBuilder.toString());
    }

    private void appendHeader(StringBuilder resultBuilder) {
        appendNameLabel(resultBuilder);
        appendFrameIndex(resultBuilder);
        resultBuilder.append(System.lineSeparator());
    }

    private void appendNameLabel(StringBuilder resultBuilder) {
        String formattedNameLabel = centerString(NAME_LABEL);
        resultBuilder.append(DELIMITER).append(formattedNameLabel).append(DELIMITER);
    }

    private void appendFrameIndex(StringBuilder resultBuilder) {
        IntStream.rangeClosed(1, Frames.MAX_FRAME_SIZE)
                .forEach(index -> {
                    String formattedFrameIndex = centerString(String.format(FRAME_INDEX_FORMAT, index));
                    resultBuilder.append(formattedFrameIndex).append(DELIMITER);
                });
    }

    private void appendBody(BowlingGame bowlingGame, StringBuilder resultBuilder) {
        appendPlayerName(bowlingGame, resultBuilder);
        appendResults(bowlingGame, resultBuilder);
        resultBuilder.append(System.lineSeparator());
        appendScore(bowlingGame, resultBuilder);
    }

    private void appendPlayerName(BowlingGame bowlingGame, StringBuilder resultBuilder) {
        String formattedPlayerName = centerString(bowlingGame.getPlayerName().getValue());
        resultBuilder.append(DELIMITER).append(formattedPlayerName).append(DELIMITER);
    }

    private void appendResults(BowlingGame bowlingGame, StringBuilder resultBuilder) {
        bowlingGame.framesStream().forEach(frame -> {
            String result = frame.getPitchings().stream()
                    .map(stringByPitching::get)
                    .collect(Collectors.joining(DELIMITER));
            String formattedResult = centerString(result);
            resultBuilder.append(formattedResult).append(DELIMITER);
        });
    }

    private void appendScore(BowlingGame bowlingGame, StringBuilder resultBuilder) {
        resultBuilder.append(DELIMITER).append(centerString("")).append(DELIMITER);
        bowlingGame.framesStream().forEach(frame -> {
            Integer score = frame.getTotalScore();
            resultBuilder.append(formatScore(score)).append(DELIMITER);
        });
    }

    private String formatScore(Integer score) {
        if (score == null) {
            return centerString("");
        }
        return centerString(String.valueOf(score));
    }

    private String centerString(String value) {
        int width = value.length() + ((CELL_WIDTH - value.length()) / 2);
        String formattedValue = String.format("%" + width + "s", value);
        return String.format("%-" + CELL_WIDTH + "s", formattedValue);
    }
}
