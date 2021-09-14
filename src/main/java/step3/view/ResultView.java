package step3.view;

import java.util.stream.Collectors;
import step3.domain.FinalFrame;
import step3.domain.Frame;
import step3.domain.Frames;
import step3.exceptions.CannotCalculateExceptions;
import step3.exceptions.SymbolDoesNotExistException;
import step3.state.State;

public class ResultView {

    private static final String OUTPUT_FRAME_FORMAT = "  %02d  |";
    private static final String OUTPUT_SCORE_FORMAT = "  %-3s |";
    private static final int FRAME_NUMBER_START = 1;
    private static final int FRAME_NUMBER_END = 10;

    public static void printHeader() {
        System.out.print("| NAME |");
        for (int i = FRAME_NUMBER_START; i <= FRAME_NUMBER_END; i++) {
            System.out.printf(OUTPUT_FRAME_FORMAT, i);
        }
        System.out.println();
    }

    public static void printResult(Frames frames, Frame frame) {
        frames.getFrames()
            .forEach(frame1 -> System.out.printf(OUTPUT_SCORE_FORMAT, frame1.getSymbol()));

        try {
            System.out.printf(OUTPUT_SCORE_FORMAT, frame.getSymbol());
        } catch (SymbolDoesNotExistException s) {
            printRightBlank();
        }

        for (int i = frame.number(); i < FRAME_NUMBER_END; i++) {
            printRightBlank();
        }

        System.out.println();

    }

    public static void printUserName(String userName) {
        System.out.printf("|  %-3s |", userName);
    }

    public static void printFinalResult(FinalFrame frame) {
        System.out.printf(OUTPUT_SCORE_FORMAT, frame.getStates()
            .stream()
            .map(State::symbol)
            .collect(Collectors.joining("|")));
    }

    public static void prinBlank() { System.out.print("|      |"); }

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
            System.out.printf(OUTPUT_SCORE_FORMAT, totalScore);
        }

        if (frame.number() == 10 && frame.isGameEnd()) {
            FinalFrame finalFrame = (FinalFrame) frame;
            System.out.printf(OUTPUT_SCORE_FORMAT, finalFrame.getLastFrameResult() + totalScore);
        }

        if (!frame.isGameEnd()) {
            for (int i = frame.number(); i <= FRAME_NUMBER_END; i++) {
                printRightBlank();
            }
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
