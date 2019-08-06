package view;

import bowling.domain.BowlingCenter;
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

    public static void printFrame(BowlingCenter center) {
        printHeaderArea();
        printPlayNameArea(center);
        printScoreArea(center);
    }

    public static void printHeaderArea() {
        System.out.println(PrintUtil.headerArea());
    }

    public static void printPlayNameArea(BowlingCenter center) {
        StringBuilder sb = new StringBuilder();

        sb.append(PrintUtil.playerNameArea(center.playerName()));
        center.displayState().stream()
                .forEach(state -> sb.append(PrintUtil.stateFrmaeArea(state)));
        sb.append(PrintUtil.idleFrameArea(center.displayState().size()));

        System.out.println(sb.toString());
    }

    public static void printScoreArea(BowlingCenter center) {
        StringBuilder sb = new StringBuilder();

        sb.append(PrintUtil.scoreFrameFirstArea());
        center.displayScore().stream()
                .forEach(score -> sb.append(PrintUtil.scoreFrameArea(score)));
        sb.append(PrintUtil.idleFrameArea(center.displayScore().size()));

        System.out.println(sb.toString());
    }
}
