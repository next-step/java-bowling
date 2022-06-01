package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.FinalFrame;
import bowling.domain.Frame;

import java.util.Optional;

public class ResultView {

    public static void printBowlingGame(String playerName, BowlingGame bowlingGame) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |\n");
        stringBuilder.append("| ").append(String.format("%4s", playerName)).append(" |");
        for (Frame frame : bowlingGame.getBowlingGame()) {
            if (frame instanceof FinalFrame) {
                stringBuilder.append(printFinalScore((FinalFrame) frame));
                continue;
            }
            stringBuilder.append(printScore(frame));
        }
        System.out.println(stringBuilder.toString());
    }

    private static String printScore(Frame frame) {
        String firstScore = Optional.ofNullable(frame.getFirstDelivery()).orElse("");
        String secondScore = Optional.ofNullable(frame.getSecondDelivery()).orElse("");

        if (!"".equals(secondScore)) {
            secondScore = "|" + secondScore;
        }

        return String.format("%3s%2s%2s", firstScore, secondScore, "|");
    }

    private static String printFinalScore(FinalFrame finalFrame) {
        String firstScore = Optional.ofNullable(finalFrame.getFirstDelivery()).orElse("");
        String secondScore = Optional.ofNullable(finalFrame.getSecondDelivery()).orElse("");
        String bonusScore = Optional.ofNullable(finalFrame.getBonusDelivery()).orElse("");

        if (!"".equals(secondScore)) {
            secondScore = "|" + secondScore;
        }

        if (!"".equals(bonusScore)) {
            bonusScore = "|" + bonusScore;
        }

        return String.format("%3s%2s%2s%2s", firstScore, secondScore, bonusScore, "|");
    }
}
