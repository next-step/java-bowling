package bowling.view;

import bowling.domain.player.Name;
import bowling.domain.Score;
import bowling.domain.frame.Frames;

import java.util.List;

import static bowling.util.StringUtils.*;

public class ResultView {
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String END = "*** 게임이 종료되었습니다 ***";

    public static void showFrames(Name player, Frames frames) {
        System.out.println(HEADER);
        System.out.println(convertName(player) + convertFrames(frames));
    }

    public static void showScores(List<Score> scores) {
        System.out.println(convertScores(scores));
    }

    public static void printEnd() {
        System.out.println(END);
    }
}
