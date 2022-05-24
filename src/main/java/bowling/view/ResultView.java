package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.Player;
import bowling.domain.frame.Frame;

import java.util.List;
import java.util.stream.IntStream;

public final class ResultView {

    private static final int START_FRAME_INDEX = 0;
    private static final int END_FRAME_INDEX = 9;
    private static final int START_FRAME_ROUND = 1;
    private static final int LAST_FRAME_ROUND = 10;

    private static final int UN_AVAILABLE = -1;

    private static final String PLAYER_NAME = "NAME";
    private static final String BODY_FORMAT = "%7s";
    private static final String VERTICAL_BAR = "|";
    private static final String NEXT_LINE = "\n";
    private static final String EXCEPTION_NOTIFICATION = "[exception]";
    private static final String BLANK = "";

    private static final StringBuilder stringBuilder = new StringBuilder();

    private void appendScores(BowlingGame bowlingGame) {
        stringBuilder.append(VERTICAL_BAR)
                .append(String.format(BODY_FORMAT, BLANK))
                .append(VERTICAL_BAR);

        IntStream.rangeClosed(START_FRAME_INDEX, END_FRAME_INDEX)
                .forEach(index -> appendSumScore(bowlingGame, index));
        appendNewLine();
    }

    private void appendSumScore(BowlingGame bowlingGame, int index) {
        stringBuilder.append(String.format(BODY_FORMAT, getScoreInGame(bowlingGame.sumScores(), index)))
                .append(VERTICAL_BAR);
    }

    private String getScoreInGame(List<Integer> sumScores, int index) {
        if (index >= sumScores.size()) {
            return BLANK;
        }

        Integer sumScore = sumScores.get(index);
        return sumScore == UN_AVAILABLE ? BLANK : String.valueOf(sumScore);
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
        appendPlayer(bowlingGame.player());
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

    public void printBowlingGameResult(BowlingGames bowlingGames) {
        appendHead();

        for (BowlingGame bowlingGame : bowlingGames.bowlingGames()) {
            appendBowlingGameResult(bowlingGame);
        }

        printContents();
    }

    private void appendBowlingGameResult(BowlingGame bowlingGame) {
        appendPlayerAndSymbols(bowlingGame);
        appendScores(bowlingGame);
    }

}
