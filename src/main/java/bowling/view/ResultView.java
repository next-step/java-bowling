package bowling.view;

import bowling.domain.*;

import java.util.Optional;

public class ResultView {
    private static final String PRINT_NORMAL_FRAME_FORMAT = "%4s%3s";
    private static final String PRINT_FINAL_FRAME_FORMAT = "%5s%4s";

    public static void printBowlingGame(String playerName, Frames frames, int frameIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |\n");
        stringBuilder.append("| ").append(String.format("%4s", playerName)).append(" |");
        FrameLinkedList frameLinkedList = frames.getFrames();
        for (int i = 0; i < frameLinkedList.size(); i++) {
            Frame frame = frameLinkedList.get(i);
            if (frame instanceof FinalFrame) {
                stringBuilder.append(printFinalScore((FinalFrame) frame));
                continue;
            }
            stringBuilder.append(printScore(frame));
        }

        stringBuilder.append("\n|      |");
        int resultScore = 0;
        for (int i = 0; i < frameLinkedList.size(); i++) {
            boolean visible = i <= frameIndex;
            Frame frame = frameLinkedList.get(i);
            int score = frame.getScore();
            if (score == 0) {
                visible = false;
            }
            resultScore += score;
            if (frame instanceof FinalFrame) {
                stringBuilder.append(printCalculatedScore(PRINT_FINAL_FRAME_FORMAT, resultScore, visible));
                continue;
            }
            stringBuilder.append(printCalculatedScore(PRINT_NORMAL_FRAME_FORMAT, resultScore, visible));
        }

        System.out.println(stringBuilder.toString());
    }

    private static String printScore(Frame frame) {
        String other;
        if (frame.getFirstState().getCountOfPins() == 0) {
            other = "";
        } else {
            other = String.valueOf(frame.getFirstState().getCountOfPins());
        }

        String firstScore = Optional.ofNullable(frame.getFirstState().getSymbol()).orElse(other);
        String secondScore = Optional.ofNullable(frame.getSecondState().getSymbol()).orElse("");

        if (!"".equals(secondScore)) {
            secondScore = "|" + secondScore;
        }

        return String.format("%3s%2s%2s", firstScore, secondScore, "|");
    }

    private static String printFinalScore(FinalFrame finalFrame) {
        String firstScore = Optional.ofNullable(finalFrame.getFirstState().getSymbol()).orElse("");
        String secondScore = Optional.ofNullable(finalFrame.getSecondState().getSymbol()).orElse("");
        String bonusScore = Optional.ofNullable(finalFrame.getBonusState().getSymbol()).orElse("");

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
