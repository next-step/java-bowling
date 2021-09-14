package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ResultView {

    public static void print(Player player) {
        System.out.println("| NAME|  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.printf("| %s |      |      |      |      |      |      |      |      |      |      | \n", player.name());
        System.out.println();
    }

    public static void print(Player player, Frames frames) {
        System.out.println("| NAME|  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        Stack<Frame> frameStack = frames.getFrames();
        List<String> frameScores = new ArrayList<>();
        for (Frame frame : frameStack) {
            frameScores.add(frame.score());
        }
        System.out.print("|  " + player.name() + "|");

        for (Frame frame : frameStack) {
            System.out.print(frame.score());
        }
        System.out.println();
        System.out.println();
    }

}
