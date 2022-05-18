package bowling.view;

import bowling.domain.PlayerName;
import bowling.frame.*;
import bowling.status.*;

public class ResultView {

    private static final int MIN_ROUND = 0;
    private static final int ONE_INDEX = 1;
    private static final int MAX_ROUND = 9;

    private static final String ONE_BLANK = " ";
    private static final String TWO_BLANK = "  ";
    private static final String NAME = "NAME";
    private static final String DIVIDER = "|";
    private static final String STRIKE_SIGNATURE = "X";
    private static final String SPARE_SIGNATURE = "/";
    private static final String GUTTER_SIGNATURE = "-";

    public void printFrameBoard(PlayerName playerName, Frames frames) {
        printRoundBoard();
        printScoreBoard(playerName, frames);
    }

    private void printRoundBoard() {
        StringBuilder roundBuilder = new StringBuilder();

        roundBuilder
                .append(DIVIDER)
                .append(TWO_BLANK)
                .append(NAME)
                .append(TWO_BLANK)
                .append(DIVIDER);

        for (int round = MIN_ROUND; round < MAX_ROUND + ONE_INDEX; round++) {
            roundBuilder
                    .append(TWO_BLANK)
                    .append(round + ONE_INDEX)
                    .append(TWO_BLANK)
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
                .append(playerName.getPlayerName())
                .append(TWO_BLANK)
                .append(DIVIDER);

        return playerNameBuilder.toString();
    }

    private String buildNormalFrame(Frames frames) {
        StringBuilder normalFrameBuilder = new StringBuilder();
        for (int round = MIN_ROUND; round < MAX_ROUND; round++) {
            Frame frameByRound = frames.findFrameByRound(Round.from(round));

            normalFrameBuilder
                    .append(TWO_BLANK)
                    .append(drawScoreByStatus(frameByRound.findMyStatus()))
                    .append(TWO_BLANK)
                    .append(DIVIDER);
        }
        return normalFrameBuilder.toString();
    }

    private String buildLastFrame(LastFrame lastFrame) {
        StringBuilder lastBuilder = new StringBuilder();

        lastBuilder
                .append(TWO_BLANK);

        for (Status myAllStatus : lastFrame.findMyAllStatus()) {
            lastBuilder.append(drawScoreByStatus(myAllStatus));
        }

        lastBuilder.append(TWO_BLANK);
        lastBuilder.append(DIVIDER);

        return lastBuilder.toString();
    }

    private String drawScoreByStatus(Status myStatus) {
        if (myStatus instanceof FirstShoot) {
            return drawFirstShootStatus((FirstShoot) myStatus);
        }

        if (myStatus instanceof Miss) {
            return drawMissStatus((Miss) myStatus);
        }

        if (myStatus instanceof Strike) {
            return STRIKE_SIGNATURE;
        }

        if (myStatus instanceof Spare) {
            return drawSpareStatus((Spare) myStatus);
        }

        return ONE_BLANK;
    }

    private String drawMissStatus(Miss myStatus) {
        String firstShootSignature = drawGutterOrScore(myStatus.findFirstShoot());
        String secondShootSignature = drawGutterOrScore(myStatus.findSecondShoot());

        return firstShootSignature + DIVIDER + secondShootSignature;
    }

    private String drawGutterOrScore(ShootScore shootScore) {
        if (shootScore.isGutter()) {
            return GUTTER_SIGNATURE;
        }
        return Integer.toString(shootScore.getShootScore());
    }


    private String drawFirstShootStatus(FirstShoot myStatus) {
        return drawGutterOrScore(myStatus.findFirstShoot());
    }

    private String drawSpareStatus(Spare myStatus) {
        return drawGutterOrScore(myStatus.findFirstShoot()) + DIVIDER + SPARE_SIGNATURE;
    }

}
