package bowling.view;

import bowling.domian.frame.Board;
import bowling.domian.player.Player;
import bowling.domian.player.Players;

import java.util.List;

public class ResultView {
    private static final String FRAME_INFO_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FRAME_EMPTY_INITIAL_FORMAT = "|      |";
    private static final String FRAME_EMPTY_FIELD_FORMAT = "      |";
    private static final String FRAME_NAME_FORMAT = "|  %s |";
    private static final String FRAME_NAME_FORMAT_FULL_SIZE = "%6s|";
    private static final String FRAME_NAME_FORMAT_FILL_SIZE = "  %-4s|";
    private static final String FRAME_SCORE_FORMAT = "  %-4d|";
    private static final String NEW_LINE = "";
    private static final String GAME_END = "게임이 종료되었습니다.";
    private static final int FRAME_STRING_SIZE = 5;
    private static final int MAX_FRAME_COUNT = 10;

    public static void printFrames(Players players) {
        System.out.println(FRAME_INFO_FORMAT);

        players.getPlayers()
                .forEach(player -> {
                    printDescs(player.getDescs(), player.getName());
                    printScores(player.getTotalScores());
                });
        System.out.println(NEW_LINE);
    }

    private static void printDescs(List<String> descs, String name) {
        StringBuffer descBuffer = new StringBuffer(String.format(FRAME_NAME_FORMAT, name));

        for (String desc : descs) {
            descBuffer.append(getDescFormat(desc));
        }

        int descCnt = descs.size();
        for (int i = descCnt; i < MAX_FRAME_COUNT; i++) {
            descBuffer.append(FRAME_EMPTY_FIELD_FORMAT);
        }

        System.out.println(descBuffer.toString());
    }

    private static String getDescFormat(String desc) {
        if (desc.length() == FRAME_STRING_SIZE) {
            return String.format(FRAME_NAME_FORMAT_FULL_SIZE, desc);
        }

        return String.format(FRAME_NAME_FORMAT_FILL_SIZE, desc);
    }

    private static void printScores(List<Integer> scores) {
        StringBuffer scoreBuffer = new StringBuffer(FRAME_EMPTY_INITIAL_FORMAT);

        for (Integer score : scores) {
            scoreBuffer.append(String.format(FRAME_SCORE_FORMAT, score));
        }

        int scoresCnt = scores.size();
        for (int i = scoresCnt; i < MAX_FRAME_COUNT; i++) {
            scoreBuffer.append(FRAME_EMPTY_FIELD_FORMAT);
        }

        System.out.println(scoreBuffer.toString());
    }

    public static void printGameEnd() {
        System.out.println(GAME_END);
    }
}
