package bowling.view;

import bowling.model.FinalFrame;
import bowling.model.Frame;
import bowling.model.Frames;
import bowling.model.NormalFrame;
import bowling.model.ShotResult;
import bowling.model.ShotResults;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printFrames(Frames frames, String playerName) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");

        List<String> frameStrings = new ArrayList<>();
        for (Frame frame : frames) {
            frameStrings.add(frameToString(frame));
        }
        System.out.printf("|  " + playerName + " |  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s| %-5s|\n", frameStrings.toArray());
    }

    private static String frameToString(Frame frame) {
        if (frame instanceof FinalFrame) {
            return shotResultsToString(((FinalFrame) frame).getShotResults());
        }
        if (((NormalFrame) frame).sumIsStrike()) {
            return replaceLastWithSpare(shotResultsToString(((NormalFrame) frame).getShotResults()));
        }
        return shotResultsToString(((NormalFrame) frame).getShotResults());
    }

    private static String shotResultsToString(ShotResults shotResults) {
        return shotResults.stream().map(OutputView::shotResultToString).reduce((accu, curr) -> accu + "|" + curr).orElse("");
    }

    private static String shotResultToString(ShotResult result) {
        if (result == ShotResult.ZERO) {
            return "-";
        }
        if (result == ShotResult.TEN) {
            return "X";
        }
        return Integer.toString(result.getNumOfPinDown());
    }

    private static String replaceLastWithSpare(String shotResultString) {
        StringBuilder sb = new StringBuilder(shotResultString);
        sb.setCharAt(shotResultString.length() - 1, '/');
        return sb.toString();
    }
}
