package view;

import domain.ResultManager;
import domain.frames.NormalFrame;
import domain.Player;

public class OutputView {

    public static void showBasicFrames(Player player, ResultManager resultManager) {
        System.out.print("| NAME |");
        for (int frameCount = 1; frameCount <= 10; frameCount++) {
            System.out.print(" " + preZero(frameCount) + " |");
        }
        System.out.println();
        System.out.print("|  " + player.getName() + " |");
//        for (int frameCount = 0; frameCount < 10; frameCount++) {
//            if (frameCount == resultManager.getFrameNumber(frameCount)-1) {
//                System.out.print("  " + resultManager.toString(frameCount) + " |");
//            } else {
//                System.out.print("    |");
//            }
//        }

        for (NormalFrame normalFrame : resultManager.getNormalFrames()) {
            System.out.print("  " + normalFrame.toString() + " |");
        }
        System.out.println();
    }

    private static String preZero(int frameCount) {
        if (frameCount < 10) {
            return "0" + frameCount;
        }
        return String.valueOf(frameCount);
    }
}
