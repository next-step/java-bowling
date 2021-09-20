package bowling.bowlingplayers.view;

import bowling.bowlingplayers.domain.Player;
import bowling.bowlingplayers.domain.frame.FinalFrame;
import bowling.bowlingplayers.domain.frame.Frame;
import bowling.bowlingplayers.domain.pitching.Pitching;

import java.util.Formatter;
import java.util.List;

public class ResultView {


    public static void showPlayBoard(Player player) {

        List<Frame> frames = player.frames().frames();
        FinalFrame finalFrame = player.frames().finalFrame();

        StringBuilder stringBuilder = new StringBuilder("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        String result = stringBuilder.append(System.lineSeparator())
                .append(framePitching(player, frames, finalFrame))
                .append(System.lineSeparator())
                .append(frameTotalScore(frames))
                .toString();

        System.out.println(result);
    }
    private static Formatter framePitching(Player player, List<Frame> frames, FinalFrame finalFrame) {
        Formatter formatter = new Formatter();
        formatter.format("|  %-4s|", player.name());

        for (int i = 0; i < 9; i++) {
            String score = framesPitchingToString(i, frames);
            formatter.format("  %-4s|", score);
        }

        String lastScore = lastScoreToString(finalFrame);
        formatter.format(" %-5s|", lastScore);
        return formatter;
    }


    private static String framesPitchingToString(int indexFrame, List<Frame> frames) {
        if(indexFrame > frames.size()-1){
            return "";
        }

        Frame frame = frames.get(indexFrame);

        return framePitchingToString(frame);
    }

    private static String lastScoreToString(FinalFrame finalFrame) {
        if (finalFrame == null) {
            return "";
        }

        String firstBonusScoreString = convertBonusResult(finalFrame.firstBonusScore());
        String secondBonusScoreString = convertBonusResult(finalFrame.secondBonusScore());

        return framePitchingToString(finalFrame) + firstBonusScoreString + secondBonusScoreString;
    }

    private static String framePitchingToString(Frame frame) {
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

    private static Formatter frameTotalScore(List<Frame> frames) {
        Formatter formatter = new Formatter();
        formatter.format("|      |");

        for (int i = 0; i < 10; i++) {
            String score = framesTotalScoreToString(i, frames);
            formatter.format("  %-4s|", score);
        }
        return formatter;
    }

    private static String framesTotalScoreToString(int indexFrame, List<Frame> frames) {
        if(indexFrame > frames.size()-1){
            return "";
        }
        
        Frame frame = frames.get(indexFrame);

        return frameTotalScoreToString(frame);
    }

    private static String frameTotalScoreToString(Frame frame) {
        if (frame.totalScore() == Pitching.IS_NULL) {
            return "";
        }
        return frame.totalScore() + "";
    }


}
