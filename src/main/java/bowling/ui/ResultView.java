package bowling.ui;

import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.domain.Score;

import static bowling.common.StringUtils.centerPadding;
import static bowling.common.SymbolConstants.SYMBOL_DELIMITER;
import static bowling.domain.Frames.FIRST_FRAME_NO;
import static bowling.domain.Frames.LAST_FRAME_NO;

public class ResultView {
    private static final String TEMPLATE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String TEMPLATE_PLAYER_NAME = "|  %s |      |      |      |      |      |      |      |      |      |      |";
    private static final String TEMPLATE_SCORES = "|      |      |      |      |      |      |      |      |      |      |      |";
    private static final String NEW_LINE = "\n";
    private static final String NONE = "";

    private ResultView() {}

    public static void print(Player player) {
        System.out.println(TEMPLATE_HEADER);
        printFrames(player);
        printScore(player);
    }

    private static void printFrames(Player player) {
        System.out.print(SYMBOL_DELIMITER.concat(centerPadding(player.getName())).concat(SYMBOL_DELIMITER));
        for(int i = FIRST_FRAME_NO; i <= LAST_FRAME_NO; i++) {
            Frame frame = player.nthFrame(i);
            System.out.print(centerPadding(frame.display()).concat(SYMBOL_DELIMITER));
        }
        System.out.print(NEW_LINE);
    }

    private static void printScore(Player player) {
        int totalScore = 0;
        System.out.print(SYMBOL_DELIMITER.concat(centerPadding(NONE).concat(SYMBOL_DELIMITER)));
        for(int i = FIRST_FRAME_NO; i <= LAST_FRAME_NO; i++) {
            Frame frame = player.nthFrame(i);
            Score score = frame.getScore();

            if(score == Score.UNSCORED) {
                System.out.print(centerPadding(NONE).concat(SYMBOL_DELIMITER));
                continue;
            }
            totalScore += score.getFallenPins();

            System.out.print(centerPadding(String.valueOf(totalScore)).concat(SYMBOL_DELIMITER));
        }
        System.out.print(NEW_LINE);
    }

    public static void printPlayerName(String playerName) {
        System.out.println(TEMPLATE_HEADER);
        System.out.printf(TEMPLATE_PLAYER_NAME, playerName);
        System.out.print(NEW_LINE);
        System.out.println(TEMPLATE_SCORES);

    }
}
