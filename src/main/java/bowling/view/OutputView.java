package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Scores;

import java.util.List;

public class OutputView {

    public static void printFrames(Player player, Frames frames) {
        List<Frame> frameList = frames.getContent();
        printHeader(frameList.size());
        printContent(player, frameList);
    }

    private static void printHeader(int frameSize) {
        System.out.print("|  NAME  |");

        for (int i = 0; i < frameSize; i++) {
            int number = i + 1;
            System.out.printf("   %s   |", number < 10 ? "0" + number : number);
        }

        System.out.println();
    }

    private static void printContent(Player player, List<Frame> frameList) {
        System.out.printf("|   %s  |", player.getName());

        for (Frame frame : frameList) {
            System.out.printf("  %s   |", getResult(frame));
        }

        System.out.println();
        System.out.println();
    }

    private static String getResult(Frame frame) {
        Scores scores = frame.getScores();
        return scores.getFirst().getContent() + "/" + scores.getSecond().getContent();
    }
}
