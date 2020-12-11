package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Pitching;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleResultView implements ResultView {
    private static final String DELIMITER = "|";
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
    public void print(Frames bowlingGame) {
        List<Frame> frames = bowlingGame.getValue();
        StringBuilder sb = new StringBuilder();
        sb.append("| NAME |");
        for (int i = 1; i <= frames.size(); i++) {
            sb.append("  ").append(String.format("%02d", i)).append("  |");
        }
        sb.append(System.lineSeparator());

        sb.append("|").append(centerString(bowlingGame.getPlayerName().getValue())).append("|");
        for (Frame frame : frames) {
            List<Pitching> status = frame.getPitchings();
            String result = status.stream()
                    .map(stringByPitching::get)
                    .collect(Collectors.joining(DELIMITER));
            sb.append(centerString(result)).append("|");
        }

        System.out.println(sb.toString());
    }

    private String centerString(String s) {
        return String.format("%-" + 6 + "s", String.format("%" + (s.length() + ((6 - s.length()) / 2) + 1) + "s", s));
    }
}
