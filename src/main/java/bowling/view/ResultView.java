package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.domain.frame.Frame;

import java.util.List;

public final class ResultView {

    private static final int START_FRAME_ROUND = 1;
    private static final int LAST_FRAME_ROUND = 10;

    private static final String PLAYER_NAME = "NAME";
    private static final String BODY_FORMAT = "%7s";
    private static final String VERTICAL_BAR = "|";
    private static final String NEXT_LINE = "\n";
    private static final String EXCEPTION_NOTIFICATION = "[exception]";
    private static final String BLANK = "";

    private static final StringBuilder stringBuilder = new StringBuilder();
    private static final int UN_AVAILABLE = -1;

    public void printBowlingGameResult(BowlingGame bowlingGame) {
        appendHead();
        appendPlayerAndSymbols(bowlingGame);
        appendScores(bowlingGame);
        printContents();
    }

    private void appendScores(BowlingGame bowlingGame) {
        stringBuilder.append(VERTICAL_BAR)
                .append(String.format(BODY_FORMAT, BLANK))
                .append(VERTICAL_BAR);

        bowlingGame.sumScores()
                .forEach(score -> stringBuilder.append(String.format(BODY_FORMAT, getScoreInGame(score)))
                .append(VERTICAL_BAR));
        appendNewLine();
    }

    private String getScoreInGame(int score) {
        return score == UN_AVAILABLE ? BLANK : String.valueOf(score);
    }

    private void appendHead() {
        appendName();

        for (int frameNumber = START_FRAME_ROUND; frameNumber <= LAST_FRAME_ROUND; frameNumber++) {
            appendFrameNumber(frameNumber);
        }

        appendNewLine();
    }

    private void appendFrameNumber(int frameNumber) {
        stringBuilder.append(String.format(BODY_FORMAT, frameNumber))
                .append(VERTICAL_BAR);
    }

    private void appendName() {
        stringBuilder.append(VERTICAL_BAR)
                .append(String.format(BODY_FORMAT, PLAYER_NAME))
                .append(VERTICAL_BAR);
    }

    private void appendNewLine() {
        stringBuilder.append(NEXT_LINE);
    }

    private void appendPlayerAndSymbols(BowlingGame bowlingGame) {
        appendPlayer(bowlingGame.getPlayer());
        appendSymbols(bowlingGame);
        appendNewLine();
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

    public void printExceptionMessage(RuntimeException runtimeException) {
        System.out.println(EXCEPTION_NOTIFICATION + runtimeException.getMessage());
    }

}
