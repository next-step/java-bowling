package bowling.view;

import bowling.domain.Pitching;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleResultView implements ResultView {
    private static final String DELIMITER = "|";
    public static final int CELL_WITH = 6;
    public static final String LABEL_NAME = "NAME";
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
        StringBuilder sb = new StringBuilder();
        appendHeader(frames, sb);
        appendResults(frames, sb);
        System.out.println(sb.toString());
    }

    private void appendResults(Frames frames, StringBuilder sb) {
        List<Frame> value = frames.getValue();
        sb.append(DELIMITER).append(centerString(frames.getPlayerName().getValue())).append(DELIMITER);
        for (Frame frame : value) {
            List<Pitching> status = frame.getPitchings();
            String result = status.stream()
                    .map(stringByPitching::get)
                    .collect(Collectors.joining(DELIMITER));
            sb.append(centerString(result)).append(DELIMITER);
        }
    }

    private void appendHeader(Frames frames, StringBuilder sb) {
        List<Frame> value = frames.getValue();
        sb.append(DELIMITER).append(centerString(LABEL_NAME)).append(DELIMITER);
        for (int i = 1; i <= value.size(); i++) {
            sb.append(centerString(String.format("%02d", i))).append(DELIMITER);
        }
        sb.append(System.lineSeparator());
    }

    private String centerString(String s) {
        return String.format("%-" + CELL_WITH + "s", String.format("%" + (s.length() + ((CELL_WITH - s.length()) / 2) + 1) + "s", s));
    }
}
