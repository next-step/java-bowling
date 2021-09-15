package bowling.bowlingdrawing.view;

import bowling.bowlingdrawing.domain.frame.FinalFrame;
import bowling.bowlingdrawing.domain.frame.Frame;
import bowling.bowlingdrawing.domain.Player;

import java.util.Formatter;
import java.util.List;

public class ResultView {


    public static void showPlayBoard(Player player) {
        Formatter formatter = new Formatter();

        List<Frame> frames = player.game().frames().frames();
        FinalFrame finalFrame = player.game().frames().finalFrame();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |")
                .append(System.lineSeparator());

        formatter.format("|  %-4s|", player.name());

        for (int i = 0; i < 9; i++) {
            String score = framesToString(i, frames);
            formatter.format("  %-4s|", score);
        }

        String lastScore = lastScoreToString(finalFrame);
        formatter.format(" %-5s|", lastScore);


        stringBuilder.append(formatter);

        String result = stringBuilder.toString();

        System.out.println(result);
    }

    private static String framesToString(int indexFrame, List<Frame> frames) {
        if(indexFrame > frames.size()-1){
            return "";
        }

        Frame frame = frames.get(indexFrame);

        return frameToString(frame);
    }

    private static String lastScoreToString(FinalFrame finalFrame) {
        if (finalFrame == null) {
            return "";
        }

        String firstBonusScoreString = convertBonusResult(finalFrame.firstBonusScore());
        String secondBonusScoreString = convertBonusResult(finalFrame.secondBonusScore());

        return frameToString(finalFrame) + firstBonusScoreString + secondBonusScoreString;
    }

    private static String frameToString(Frame frame) {
        String firstScoreString = convertFirstResult(frame.firstScore());
        String secondScoreString = convertNextResult(frame.secondScore());

        if(frame.strike()) {
            return "X";
        }
        if (frame.spare()) {
            return frame.firstScore() + "|/";
        }

        return firstScoreString + secondScoreString;
    }

    private static String convertFirstResult(int score) {
        if (score == -1) {
            return "";
        }

        if (score == 0) {
            return "-";
        }

        return score + "";
    }

    private static String convertNextResult(int score) {
        if (score == -1) {
            return "";
        }

        if (score == 0) {
            return "|-";
        }

        return "|" + score;
    }

    private static String convertBonusResult(int score) {
        if (score == -1) {
            return "";
        }

        if (score == 10) {
            return "|X";
        }

        if (score == 0) {
            return "|-";
        }

        return "|" + score;
    }

}
