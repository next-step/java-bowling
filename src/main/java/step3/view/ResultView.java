package step3.view;

import java.util.List;
import java.util.stream.Collectors;
import step3.domain.FinalFrame;
import step3.domain.Frame;
import step3.domain.Frames;
import step3.exceptions.SymbolDoesNotExistException;

public class ResultView {

    public static void printHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printResult(Frames frames, Frame frame) {
        frames.getFrames()
            .stream()
            .map(frame1 -> frame1.getSymbol())
            .forEach(s -> System.out.printf("  %-3s |", s));

        try {
            System.out.printf("  %-3s |", frame.getSymbol());
        } catch (SymbolDoesNotExistException s) {

        }

        if (frame.number() == 10) {
            printFinalResult((FinalFrame) frame);
        }
        System.out.println();
    }

    public static void printUserName(String userName) {
        System.out.print("|  PJS |");
    }

    public static void printFinalResult(FinalFrame frame) {
        System.out.printf("  %-3s |", frame.getStates()
            .stream()
            .map(state -> state.symbol())
            .collect(Collectors.joining("|")));
    }

}
