package bowling;

import java.util.List;

public class BowlingGameRunner {
    public static void main(String[] args) {
        ConsoleInputView consoleInputView = new ConsoleInputView();
        String playerName = consoleInputView.getPlayerName();

        BowlingGame bowlingGame = BowlingGame.init(playerName);

        while (!bowlingGame.isEnd()) {
            int knockDownPins = consoleInputView.getKnockDownPins(bowlingGame.getCurrentFrameIndex());
            bowlingGame.setKnockDownPins(knockDownPins);
            print(bowlingGame);
        }
    }

    private static void print(BowlingGame bowlingGame) {
        List<Frame> frames = bowlingGame.getFrames();
        StringBuilder sb = new StringBuilder();
        sb.append("| NAME |");
        for (int i = 1; i <= frames.size(); i++) {
            sb.append("  ").append(String.format("%02d", i)).append("  |");
        }
        sb.append(System.lineSeparator());

        sb.append("|").append(centerString(6, bowlingGame.getPlayerName())).append("|");
        for (Frame frame : frames) {
            sb.append(centerString(6, frame.getStatus().toString())).append("|");
        }

        System.out.println(sb.toString());
    }

    public static String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + ((width - s.length()) / 2) + 1) + "s", s));
    }
}
