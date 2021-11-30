package bowling.ui;

import bowling.domain.frame.Frame;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;
import bowling.domain.value.Player;

import java.util.stream.Collectors;

public class ResultView {
    private static final int START_FRAME = 1;
    private static final int FINAL_FRAME = 10;
    private static final String PLAYER_NAME = "NAME";
    private static final String DELIMITER = "|";
    private static final String LINE = "|";
    private static final String ENTER = "\r\n";

    public void printBowlingResult(Frame frame, Player player) {
        printHead();
        printBody(frame, player);
    }

    private void printHead() {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, PLAYER_NAME);

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            printFrame(bowlingBuilder, String.valueOf(i));
        }

        System.out.println(bowlingBuilder);
    }

    private void printBody(Frame frame, Player player) {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, player.getName());

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            FramePins framePins = frame.getPins(FrameNumber.from(i));
            printFrame(bowlingBuilder, printScore(framePins));
        }

        bowlingBuilder.append(ENTER);
        System.out.println(bowlingBuilder.toString());
    }

    private void printPlayerName(StringBuilder bowlingBuilder, String playerName) {
        bowlingBuilder.append(LINE).append(String.format("%7s", playerName)).append(LINE);
    }

    private void printFrame(StringBuilder bowlingBuilder, String frameNumber) {
        bowlingBuilder.append(String.format("%7s", frameNumber)).append(LINE);
    }

    private String printScore(FramePins framePins) {
        return framePins.getPins().stream()
                .map(Pins::getMark)
                .collect(Collectors.joining(DELIMITER));
    }
}
