package bowling.view;

import bowling.domain.Board;
import bowling.domain.Boards;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Optional;

public class OutputView {

    public static void printBoards(Boards boards) {
        for (Board board : boards.getContent()) {
            printBoard(board);
        }
    }

    private static void printBoard(Board board) {
        Frames frames = board.getFrames();
        List<Frame> frameList = frames.getContent();
        printHeader(frameList.size());
        printScore(board.getPlayer(), frameList);
        printTotalScores(frameList);
    }

    private static void printHeader(int frameSize) {
        System.out.print("|  NAME  |");

        for (int i = 0; i < frameSize; i++) {
            System.out.printf("   %02d   |", i + 1);
        }

        System.out.println();
    }

    private static void printScore(Player player, List<Frame> frames) {
        System.out.printf("|   %s  |", player.getName());

        for (Frame frame : frames) {
            System.out.printf("%s|", ViewResult.parseFrameScore(frame));
        }

        System.out.println();
    }

    private static void printTotalScores(List<Frame> frames) {
        int sum = 0;
        System.out.print("|        |");

        for (Frame frame : frames) {
            sum = printTotalScore(sum, frame);
        }

        System.out.println();
        System.out.println();
    }

    private static int printTotalScore(int sum, Frame frame) {
        Optional<Score> totalScoreOptional = frame.calculateTotalScore();

        if (!totalScoreOptional.isPresent()) {
            System.out.print("        |");
            return sum;
        }

        sum += totalScoreOptional.map(Score::getContent).get();
        System.out.printf("%s|", formatBlank(String.valueOf(sum)));

        return sum;
    }

    private static String formatBlank(String input) {
        switch (input.length()) {
            case 1: return String.format("   %s    ", input);
            case 2: return String.format("   %s   ", input);
            default: return String.format("  %s   ", input);
        }
    }
}
