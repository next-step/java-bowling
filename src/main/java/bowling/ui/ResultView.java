package bowling.ui;

import bowling.domain.frame.Frame;
import bowling.domain.value.BowlingClub;
import bowling.domain.value.BowlingGame;

public class ResultView {
    private static final int START_FRAME = 1;
    private static final int FINAL_FRAME = 10;
    private static final String PLAYER_NAME = "NAME";
    private static final String LINE = "|";
    private static final String EMPTY = "";

    public void printBowlingResult(BowlingClub bowlingClub) {
        printHead();

        bowlingClub.getBowlingGames().forEach(bowlingGame -> {
            printMark(bowlingGame);
            printScore(bowlingGame);
        });
    }

    private void printHead() {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, PLAYER_NAME);

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            printFrame(bowlingBuilder, String.valueOf(i));
        }

        System.out.println(bowlingBuilder);
    }

    private void printMark(BowlingGame bowlingGame) {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, bowlingGame.getPlayerName());

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            Frame frame = bowlingGame.getFrames(i);
            printFrame(bowlingBuilder, frame.getMark());
        }

        System.out.println(bowlingBuilder);
    }

    private void printPlayerName(StringBuilder bowlingBuilder, String playerName) {
        bowlingBuilder.append(LINE).append(String.format("%7s", playerName)).append(LINE);
    }

    private void printFrame(StringBuilder bowlingBuilder, String frameNumber) {
        bowlingBuilder.append(String.format("%7s", frameNumber)).append(LINE);
    }

    private void printScore(BowlingGame bowlingGame) {
        StringBuilder bowlingBuilder = new StringBuilder();
        printPlayerName(bowlingBuilder, EMPTY);

        for (int i = START_FRAME; i <= FINAL_FRAME; i++) {
            String score = bowlingGame.getScore(i);
            printFrame(bowlingBuilder, score);
        }

        System.out.println(bowlingBuilder);
    }
}
