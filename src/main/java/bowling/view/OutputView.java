package bowling.view;

import bowling.model.Frame;
import bowling.model.Frames;
import bowling.model.ShotResult;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printFrames(Frames frames, String playerName) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");

        List<String> frameStrings = new ArrayList<>();
        for (Frame frame : frames) {
            frameStrings.add(frame.toString());
        }
        System.out.printf("|  " + playerName + " |  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s| %-5s|\n", frameStrings.toArray());
    }

    public static void printShotResult(ShotResult shotResult, int currentPlayingFrameIndex) {
        System.out.println(currentPlayingFrameIndex + 1 + "프레임 투구 : " + shotResult);
    }
}
