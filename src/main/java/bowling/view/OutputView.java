package bowling.view;

import bowling.domain.dto.FrameResultsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public static final String FRAME_DELIMETER = "|";
    public static final List<String> frameNumberList = new ArrayList<>();

    static {
        frameNumberList.add(" NAME ");
        frameNumberList.addAll(IntStream.rangeClosed(1, 10)
                .mapToObj(s -> String.format("%02d", s))
                .map(String::valueOf)
                .collect(Collectors.toList()));
    }

    public static void printFrameResult(FrameResultsDto results) {
        printResult(frameNumberList);
        
        System.out.println();
        
        printResult(results.results());

        System.out.println();
        System.out.println();
    }

    private static void printResult(List<String> results) {
        printFrameDelimeter();
        for (String result : results) {
            System.out.print(formatMessage(result));
            printFrameDelimeter();
        }
    }

    private static void printFrameDelimeter() {
        System.out.print(FRAME_DELIMETER);
    }

    private static String formatMessage(String message) {
        return String.format("%6s", String.format("%-4s", message));
    }

}
