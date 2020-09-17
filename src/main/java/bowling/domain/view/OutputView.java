package bowling.domain.view;

import bowling.domain.frame.Frame;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;
import bowling.domain.score.ScoreConverter;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String BLANK = " ";
    private static final int MAX_NAME_LENGTH = 5;
    private static final String BOARD_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private static Map<Integer, String> map = new HashMap<>();
    private static String[] scores = new String[11];


    private static void arrayInit(Player player) {
        System.out.println(BOARD_TITLE);
        scores[0] = ("|" + makeSpaceName(player.getName()) + "|");
        for (int i = 1; i < scores.length; i++) {
            scores[i] = "      |";
        }
    }

    private static void printScore() {
        for (int i = 0; i < scores.length; i++) {
            System.out.print(makeSpaceName(scores[i]));
        }
        System.out.print(lineSeparator());
    }

    public static void getScoreBoard(Player player, Frame frame) {
        arrayInit(player);

        map.put(frame.getIndex(), ScoreConverter.convert(frame));

        scoreAppend(frame, map);
        printScore();
    }

    private static void scoreAppend(Frame frame, Map map) {
        for (int i = 1; i <= frame.getIndex(); i++) {
            scores[i] = makeSpaceScore(frame.getPins(), String.valueOf(map.get(i)));
        }
    }

    /**
     * 이름 출력시 5글자까지 반복하여 공백을 더해준다.
     *
     * @param userName
     * @return
     */
    private static String makeSpaceName(String userName) {
        if (userName.length() > MAX_NAME_LENGTH) {
            return userName;
        }
        if (userName.length() % 2 == 1) {
            return makeSpaceName(BLANK + userName);
        }
        return makeSpaceName(userName + BLANK);
    }

    private static String makeSpaceScore(Pins pins, String score) {
        if (score.length() > MAX_NAME_LENGTH) {
            return score;
        }
        return makeSpaceName(score) + "|";
    }
}
