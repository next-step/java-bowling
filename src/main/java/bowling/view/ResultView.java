package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.frame.*;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.AbstractFrame;

import java.util.Optional;

public class ResultView {
    private static final String PRINT_NORMAL_FRAME_FORMAT = "%4s%3s";
    private static final String PRINT_FINAL_FRAME_FORMAT = "%5s%4s";

    public static void printBowlingGame(Players players, int frameIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        buildFrameIndex(stringBuilder);

        for (Player player : players.getPlayers().keySet()) {
            buildEachPlayerFrameIndex(player.getName(), stringBuilder);

            FrameLinkedList frameLinkedList = players.getFrameLinkedList(player);
            buildEachFrameScore(stringBuilder, frameLinkedList);
            buildTotalScore(frameIndex, stringBuilder, frameLinkedList);
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    private static void buildFrameIndex(StringBuilder stringBuilder) {
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |\n");
    }

    private static void buildEachPlayerFrameIndex(String playerName, StringBuilder stringBuilder) {
        stringBuilder.append("| ").append(String.format("%4s", playerName)).append(" |");
    }

    private static void buildEachFrameScore(StringBuilder stringBuilder, FrameLinkedList frameLinkedList) {
        for (int i = 0; i < frameLinkedList.size(); i++) {
            AbstractFrame abstractFrame = frameLinkedList.get(i);
            if (abstractFrame instanceof FinalFrame) {
                stringBuilder.append(printFinalScore((FinalFrame) abstractFrame));
                continue;
            }
            stringBuilder.append(printScore(abstractFrame));
        }
    }

    private static void buildTotalScore(int frameIndex, StringBuilder stringBuilder, FrameLinkedList frameLinkedList) {
        stringBuilder.append("\n|      |");
        int resultScore = 0;
        for (int i = 0; i < frameLinkedList.size(); i++) {
            Frame frame = frameLinkedList.get(i);
            int score = frame.getScore();
            resultScore += score;
            if (frame instanceof FinalFrame) {
                stringBuilder.append(printCalculatedScore(PRINT_FINAL_FRAME_FORMAT, resultScore, isVisible(frameIndex, i, score)));
                continue;
            }
            stringBuilder.append(printCalculatedScore(PRINT_NORMAL_FRAME_FORMAT, resultScore, isVisible(frameIndex, i, score)));
        }
    }

    private static boolean isVisible(int frameIndex, int i, int score) {
        boolean visible = i <= frameIndex;
        if (score == 0) {
            visible = false;
        }
        return visible;
    }

    private static String printScore(AbstractFrame abstractFrame) {
        String firstScore = Optional.ofNullable(abstractFrame.symbolOfFirstFrame()).orElse(defaultScore(abstractFrame));
        String secondScore = Optional.ofNullable(abstractFrame.symbolOfSecondFrame()).orElse("");

        if (!"".equals(secondScore)) {
            secondScore = "|" + secondScore;
        }

        return String.format("%3s%2s%2s", firstScore, secondScore, "|");
    }

    private static String defaultScore(AbstractFrame abstractFrame) {
        if (abstractFrame.fallenPinsCountOfFirstFrame() == 0) {
            return "";
        }
        return String.valueOf(abstractFrame.fallenPinsCountOfFirstFrame());
    }

    private static String printFinalScore(FinalFrame finalFrame) {
        String firstScore = Optional.ofNullable(finalFrame.symbolOfFirstFrame()).orElse("");
        String secondScore = Optional.ofNullable(finalFrame.symbolOfSecondFrame()).orElse("");
        String bonusScore = Optional.ofNullable(finalFrame.symbolOfBonusFrame()).orElse("");

        if (!"".equals(secondScore)) {
            secondScore = "|" + secondScore;
        }

        if (!"".equals(bonusScore)) {
            bonusScore = "|" + bonusScore;
        }

        return String.format("%3s%2s%2s%2s", firstScore, secondScore, bonusScore, "|");
    }

    private static String printCalculatedScore(String format, int score, boolean visible) {
        return String.format(format, visible ? String.valueOf(score) : "", "|");
    }
}
