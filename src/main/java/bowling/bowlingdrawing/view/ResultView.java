package bowling.bowlingdrawing.view;

import bowling.bowlingdrawing.domain.frame.Frame;
import bowling.bowlingdrawing.domain.Player;

import java.util.Formatter;
import java.util.List;

public class ResultView {


    public static void showPlayBoard(Player player) {
        Formatter formatter = new Formatter();

        List<Frame> frames = player.game().frames().frames();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |")
                .append(System.lineSeparator());

        formatter.format("|  %-4s|", player.name());

        for (int i = 0; i < 10; i++) {
            String score = scoreToString(i, frames);
            formatter.format("  %-4s|", score);
        }

        stringBuilder.append(formatter);

        String result = stringBuilder.toString();

        System.out.println(result);
    }

    private static String scoreToString(int indexFrame, List<Frame> frames) {
        if(indexFrame > frames.size()-1){
            return "";
        }

        Frame frame = frames.get(indexFrame);
        if(frame.strike()) {
            return "X";
        }
        if (frame.spare()) {
            return frame.firstScore() + "|/";
        }
        if (frame.secondScore() == -1) {
            return frame.firstScore() + "";
        }
        if (frame.secondScore() == 0) {
            return frame.firstScore() + "|-";
        }
        return frame.firstScore() + "|" + frame.secondScore();
    }
}
