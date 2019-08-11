package view;

import bowling.domain.BowlingCenter;
import bowling.domain.Player;
import bowling.domain.Players;
import view.util.PrintUtil;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 15:11
 */
public class ConsoleOutView {

    public static void printHeaderArea() {
        System.out.println(PrintUtil.headerArea());
    }

    public static void printFrame(BowlingCenter center, Players players) {
        printHeaderArea();
        players.getPlayers().stream()
                .forEach(player -> printPlayNameArea(center, player));
    }

    public static void printPlayNameArea(BowlingCenter center, Player player) {
        StringBuilder sb = new StringBuilder();

        sb.append(PrintUtil.playerNameArea(player.getName()));

        center.displayState(player).stream()
                .forEach(state -> sb.append(PrintUtil.stateFrmaeArea(state)));
        sb.append(PrintUtil.idleFrameArea(center.displayState(player).size()));
        System.out.println(sb.toString());

        printScoreArea(center, player);
    }

    public static void printScoreArea(BowlingCenter center, Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(PrintUtil.scoreFrameFirstArea());

        center.displayScore(player).stream()
                .forEach(score -> sb.append(PrintUtil.scoreFrameArea(score)));
        sb.append(PrintUtil.idleFrameArea(center.displayScore(player).size()));

        System.out.println(sb.toString());
    }
}
