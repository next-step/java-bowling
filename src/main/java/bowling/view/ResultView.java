package bowling.view;

import bowling.domain.Score;
import bowling.domain.player.Players;

import java.util.List;

import static bowling.util.StringUtils.*;

public class ResultView {
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String END = "*** 게임이 종료되었습니다 ***";

    public static void showPlayers(Players players) {
        System.out.println(HEADER);
        players.getPlayers().forEach(player -> {
            String[] playerStr = splitPlayer(player.toString());
            showFrames(playerStr[0], playerStr[1]);
            showScores(player.totalScores());
        });
    }

    public static void showFrames(String name, String frames) {
        System.out.println(convertName(name) + convertFrames(frames));
    }

    public static void showScores(List<Score> scores) {
        System.out.println(convertScores(scores));
    }

    public static void printEnd() {
        System.out.println(END);
    }
}
