package bowling.view;

import bowling.domain.Pitching;
import bowling.domain.frame.Frames;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleResultView implements ResultView {
    private static final String DELIMITER = "|";
    private static final int CELL_WIDTH = 6;
    private static final String NAME_LABEL = "NAME";
    public static final String FRAME_INDEX_FORMAT = "%02d";
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
    public void print(Frames frames) {
        StringBuilder resultBuilder = new StringBuilder();
        appendHeader(frames, resultBuilder);
        appendBody(frames, resultBuilder);
        System.out.println(resultBuilder.toString());
    }

    private void appendHeader(Frames frames, StringBuilder resultBuilder) {
        appendNameLabel(resultBuilder);
        appendFrameIndex(frames, resultBuilder);
        resultBuilder.append(System.lineSeparator());
    }

    private void appendNameLabel(StringBuilder resultBuilder) {
        String formattedNameLabel = centerString(NAME_LABEL);
        resultBuilder.append(DELIMITER).append(formattedNameLabel).append(DELIMITER);
    }

    private void appendFrameIndex(Frames frames, StringBuilder resultBuilder) {
        frames.stream().forEach(frame -> {
            String formattedFrameIndex = centerString(String.format(FRAME_INDEX_FORMAT, frame.getIndex()));
            resultBuilder.append(formattedFrameIndex).append(DELIMITER);
        });
    }

    private void appendBody(Frames frames, StringBuilder resultBuilder) {
        appendPlayerName(frames, resultBuilder);
        appendResults(frames, resultBuilder);
    }

    private void appendPlayerName(Frames frames, StringBuilder resultBuilder) {
        String formattedPlayerName = centerString(frames.getPlayerName().getValue());
        resultBuilder.append(DELIMITER).append(formattedPlayerName).append(DELIMITER);
    }

    private void appendResults(Frames frames, StringBuilder resultBuilder) {
        frames.stream().forEach(frame -> {
            String result = frame.getPitchings().stream()
                    .map(stringByPitching::get)
                    .collect(Collectors.joining(DELIMITER));
            String formattedResult = centerString(result);
            resultBuilder.append(formattedResult).append(DELIMITER);
        });
    }

    private String centerString(String s) {
        return String.format("%-" + CELL_WIDTH + "s", String.format("%" + (s.length() + ((CELL_WIDTH - s.length()) / 2) + 1) + "s", s));
    }
}
