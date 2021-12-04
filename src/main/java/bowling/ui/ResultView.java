package bowling.ui;

import bowling.domain.frame.Frame;
import bowling.domain.value.BowlingClub;
import bowling.domain.value.Player;

public class ResultView {
    private static final int START_FRAME = 1;
    private static final int FINAL_FRAME = 10;
    private static final String PLAYER_NAME = "NAME";
    private static final String LINE = "|";
    private static final String ENTER = "\r\n";
    private static final String EMPTY = "";

    public void printBowlingResult(BowlingClub bowlingClub, Player player) {
        printHead();
        printMark(bowlingClub, player);
        printScore(bowlingClub);
    }

    private void printHead() {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, PLAYER_NAME);

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            printFrame(bowlingBuilder, String.valueOf(i));
        }

        System.out.println(bowlingBuilder);
    }

    private void printMark(BowlingClub bowlingClub, Player player) {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, player.getName());

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            Frame frame = bowlingClub.getFrame(i);
            printFrame(bowlingBuilder, frame.mark());
        }

        System.out.println(bowlingBuilder);
    }

    private void printPlayerName(StringBuilder bowlingBuilder, String playerName) {
        bowlingBuilder.append(LINE).append(String.format("%7s", playerName)).append(LINE);
    }

    private void printFrame(StringBuilder bowlingBuilder, String frameNumber) {
        bowlingBuilder.append(String.format("%7s", frameNumber)).append(LINE);
    }

    private void printScore(BowlingClub bowlingClub) {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, EMPTY);

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            String score = bowlingClub.getScore(i);
            printFrame(bowlingBuilder, score);
        }

        bowlingBuilder.append(ENTER);
        System.out.println(bowlingBuilder);
    }
}
