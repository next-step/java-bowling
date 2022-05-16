package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.exception.InvalidPinsException;

import java.util.List;

public final class ResultView {

    private static final int START_FRAME_ROUND = 1;
    private static final int LAST_FRAME_ROUND = 10;

    private static final String PLAYER_NAME = "NAME";
    private static final String BODY_FORMAT = "%7s";
    private static final String VERTICAL_BAR = "|";
    private static final String NEXT_LINE = "\n";

    private static final StringBuilder stringBuilder = new StringBuilder();

    public void printBowlingGameResult(BowlingGame bowlingGame) {
        appendHead();
        appendPlayerAndSymbols(bowlingGame);
        printContents();
    }

    private void appendHead() {
        appendName();

        for (int frameNumber = START_FRAME_ROUND; frameNumber <= LAST_FRAME_ROUND; frameNumber++) {
            appendFrameNumber(frameNumber);
        }

        appendNewLine();
    }

    private void appendFrameNumber(int frameNumber) {
        stringBuilder.append(String.format(BODY_FORMAT, frameNumber)).append(VERTICAL_BAR);
    }

    private void appendName() {
        stringBuilder.append(VERTICAL_BAR).append(String.format(BODY_FORMAT, PLAYER_NAME)).append(VERTICAL_BAR);
    }

    private void appendNewLine() {
        stringBuilder.append(NEXT_LINE);
    }

    private void appendPlayerAndSymbols(BowlingGame bowlingGame) {
        appendPlayer(bowlingGame.getPlayer());
        appendSymbols(bowlingGame);
    }

    private void appendPlayer(Player player) {
        stringBuilder.append(VERTICAL_BAR).append(String.format(BODY_FORMAT, player)).append(VERTICAL_BAR);
    }

    private void appendSymbols(BowlingGame bowlingGame) {
        List<Frame> frames = bowlingGame.getFrames();
        for (Frame frame : frames) {
            String bodyFormat = String.format(BODY_FORMAT, frame.getSymbol());
            stringBuilder.append(bodyFormat).append(VERTICAL_BAR);
        }
    }

    private void printContents() {
        System.out.println(stringBuilder);
        clear();
    }

    private void clear() {
        stringBuilder.setLength(0);
    }

    public void printExceptionMessage(InvalidPinsException invalidPinsException) {
        System.out.println(invalidPinsException.getMessage());
    }

}
