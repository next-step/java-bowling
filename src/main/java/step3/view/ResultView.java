package step3.view;

import java.util.stream.Collectors;
import step3.domain.FinalFrame;
import step3.domain.Frame;
import step3.domain.Frames;
import step3.exceptions.CannotCalculateExceptions;
import step3.exceptions.SymbolDoesNotExistException;
import step3.state.State;

public class ResultView {

    public static void printHeader() {
        System.out.println(
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printResult(Frames frames, Frame frame) {
        frames.getFrames()
            .forEach(frame1 -> System.out.printf("  %-3s |", frame1.getSymbol()));

        try {
            System.out.printf("  %-3s |", frame.getSymbol());
        } catch (SymbolDoesNotExistException s) {
            printRightBlank();
        }

        for (int i = frame.number(); i < 10; i++) {
            printRightBlank();
        }

        System.out.println();

    }

    public static void printUserName(String userName) {
        System.out.print("|  PJS |");
    }

    public static void printFinalResult(FinalFrame frame) {
        System.out.printf("  %-3s |", frame.getStates()
            .stream()
            .map(State::symbol)
            .collect(Collectors.joining("|")));
    }

    public static void prinBlank() {
        System.out.print("|      |");
    }

    public static void printRightBlank() {
        System.out.print("      |");
    }

    public static void printScoreResult(Frames frames, Frame frame) {
        int totalScore = 0;
        for (Frame frame1 : frames.getFrames()) {
            int currentFrameScore = printResultScoreByFrame(frame1, totalScore);
            if (currentFrameScore == -1) {
                printRightBlank();
                continue;
            }
            totalScore += currentFrameScore;
            System.out.printf("  %-3s |", totalScore);
        }

        if (frame.number() == 10 && frame.isGameEnd()) {
            FinalFrame finalFrame = (FinalFrame) frame;
            System.out.printf("  %-3s |", finalFrame.getLastFrameResult() + totalScore);
        }

        System.out.println();
    }

    private static int printResultScoreByFrame(Frame frame1, int totalScore) {
        try {
            return frame1.getScore().getScore();
        } catch (CannotCalculateExceptions c) {
            return -1;
        }
    }
}
