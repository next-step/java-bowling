package bowling;

import java.util.List;

public class ConsoleResultView {
    public void print(BowlingGame bowlingGame) {
        List<Frame> frames = bowlingGame.getFrames();
        StringBuilder sb = new StringBuilder();
        sb.append("| NAME |");
        for (int i = 1; i <= frames.size(); i++) {
            sb.append("  ").append(String.format("%02d", i)).append("  |");
        }
        sb.append(System.lineSeparator());

        sb.append("|").append(centerString(bowlingGame.getPlayerName())).append("|");
        for (Frame frame : frames) {
            sb.append(centerString(frame.getStatus().toString())).append("|");
        }

        System.out.println(sb.toString());
    }

    private String centerString(String s) {
        return String.format("%-" + 6 + "s", String.format("%" + (s.length() + ((6 - s.length()) / 2) + 1) + "s", s));
    }
}
