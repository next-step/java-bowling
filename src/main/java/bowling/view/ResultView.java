package bowling.view;

import java.util.stream.IntStream;

public class ResultView {

    private static final String SEPARATOR = "|";
    private static final String NAME = "NAME";

    private ResultView() {
    }

    public static void printResult() {
        System.out.print(String.format("%s %6s %s", SEPARATOR, NAME, SEPARATOR));
        IntStream.range(1, 10)
                .mapToObj(number -> String.format("%02d", number))
                .forEach(string -> System.out.print(
                        String.format("%6s %s", string, SEPARATOR)
                ));
    }
}
