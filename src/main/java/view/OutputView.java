package view;

import domain.NormalFrame;
import domain.Player;

public class OutputView {

    public static void showBasicFrames(Player player, NormalFrame normalFrame) {
        System.out.print("| NAME |");
        for (int frameCount = 1; frameCount <= 10; frameCount++) {
            System.out.print(" " + preZero(frameCount) + " |");
        }
        System.out.println();
        System.out.print("|  " + player.getName() + " |");
        for (int frameCount = 1; frameCount <= 10; frameCount++) {
            if (frameCount == normalFrame.getFrameNumber()) {
                System.out.print("  " + normalFrame.toString() + " |");
            } else {
                System.out.print("    |");
            }
        }
    }

    private static String preZero(int frameCount) {
        if (frameCount < 10) {
            return "0" + frameCount;
        }
        return String.valueOf(frameCount);
    }
}
