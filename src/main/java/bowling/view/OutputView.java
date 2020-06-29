package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

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
            System.out.printf("   %02d   |", i + 1);
        }

        System.out.println();
    }

    private static void printContent(Player player, List<Frame> frameList) {
        System.out.printf("|   %s  |", player.getName());

        for (Frame frame : frameList) {
            System.out.printf("%s|", ViewResult.parseScores(frame.getScores()));
        }

        System.out.println();
        System.out.println();
    }
}
