package bowling.view;

import java.util.stream.IntStream;

public class ResultView {

    private static final String PRINT_START_FORMAT = "|%5s |";
    private static final String PRINT_FORMAT = "%4s |";
    private ResultView() {}

    public static void bowlingFrameListPrint(){
        String name = "NAME";
        System.out.printf(PRINT_START_FORMAT, name);

        IntStream.rangeClosed(1, 10).forEach(i -> System.out.printf(PRINT_FORMAT, String.format("%02d", i)));
        System.out.println();
    }

    public static void userBowlingFrameListPrint(String userName) {
        System.out.printf(PRINT_START_FORMAT, userName);
        IntStream.rangeClosed(1, 10).forEach(i -> System.out.printf(PRINT_FORMAT, ""));
    }
}
