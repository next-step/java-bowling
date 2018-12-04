package view;

import domain.ResultManager;
import domain.frames.Frame;
import domain.frames.NormalFrame;
import domain.Player;

public class OutputView {

    public static void showBasicFrames(Player player, ResultManager resultManager) {
        System.out.print("| NAME |");
        for (int frameCount = 1; frameCount <= 10; frameCount++) {
            System.out.print("  " + leadingZero(frameCount) + "  |");
        }
        System.out.println();
        System.out.print("|  " + player.getName() + " |");
        for (Frame frame : resultManager.getNormalFrames()) {
            NormalFrame normalFrame = (NormalFrame)frame;
            System.out.print(" " + normalFrame.showResultFirst() + normalFrame.showResultSecond() + " |");
        }
        System.out.println();
    }

    private static String leadingZero(int frameCount) {
        if (frameCount < 10) {
            return "0" + frameCount;
        }
        return String.valueOf(frameCount);
    }
}
