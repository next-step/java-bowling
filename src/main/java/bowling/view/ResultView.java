package bowling.view;

import bowling.domain.PlayerName;
import bowling.frame.*;
import bowling.status.*;

import java.util.List;

public class ResultView {

    private static final int MIN_ROUND = 0;
    private static final int ONE_INDEX = 1;
    private static final int MAX_ROUND = 9;

    private static final String ONE_BLANK = " ";
    private static final String TWO_BLANK = "  ";
    private static final String NAME = "NAME";
    private static final String DIVIDER = "|";

    public void printFrameBoard(PlayerName playerName, Frames frames) {
        printRoundBoard();
        printScoreBoard(playerName, frames);
    }

    private void printRoundBoard() {
        StringBuilder roundBuilder = new StringBuilder();

        roundBuilder
                .append(DIVIDER)
                .append(TWO_BLANK)
                .append(boardFormat(NAME))
                .append(ONE_BLANK)
                .append(DIVIDER);

        for (int round = MIN_ROUND; round < MAX_ROUND + ONE_INDEX; round++) {
            roundBuilder
                    .append(TWO_BLANK)
                    .append(boardFormat(round + ONE_INDEX))
                    .append(DIVIDER);

        }

        System.out.println(roundBuilder);
    }

    private void printScoreBoard(PlayerName playerName, Frames frames) {
        StringBuilder scoreBuilder = new StringBuilder();

        scoreBuilder.append(buildPlayerName(playerName));
        scoreBuilder.append(buildNormalFrame(frames));
        scoreBuilder.append(buildLastFrame((LastFrame) frames.findFrameByRound(Round.from(MAX_ROUND))));

        System.out.println(scoreBuilder);
    }

    private String buildPlayerName(PlayerName playerName) {
        StringBuilder playerNameBuilder = new StringBuilder();
        playerNameBuilder
                .append(DIVIDER)
                .append(TWO_BLANK)
                .append(boardFormat(playerName.getPlayerName()))
                .append(TWO_BLANK)
                .append(DIVIDER);

        return playerNameBuilder.toString();
    }

    private String buildNormalFrame(Frames frames) {
        StringBuilder normalFrameBuilder = new StringBuilder();
        for (int round = MIN_ROUND; round < MAX_ROUND; round++) {
            Frame frameByRound = frames.findFrameByRound(Round.from(round));
            Status myStatus = frameByRound.findMyStatus();

            normalFrameBuilder
                    .append(TWO_BLANK)
                    .append(boardFormat(myStatus.board()))
                    .append(DIVIDER);
        }
        return normalFrameBuilder.toString();
    }

    private String buildLastFrame(LastFrame lastFrame) {
        StringBuilder lastBuilder = new StringBuilder();
        String lastFrameStatus = lastFrameBoard(lastFrame.findMyAllStatus());

        lastBuilder
                .append(TWO_BLANK)
                .append(boardFormat(lastFrameStatus))
                .append(DIVIDER);

        return lastBuilder.toString();
    }

    private String lastFrameBoard(List<Status> myAllStatus) {
        StringBuilder lastFrameBoardBuilder = new StringBuilder();

        for (Status myStatus : myAllStatus) {
            lastFrameBoardBuilder.append(myStatus.board());
        }

        return lastFrameBoardBuilder.toString();
    }

    private String boardFormat(String board) {
        return String.format("%-3s", board);
    }

    private String boardFormat(int board) {
        return String.format("%-3s", board);
    }
}
