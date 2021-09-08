package bowling.view;

import bowling.domain.Name;
import bowling.domain.frame.Frames;

import java.util.stream.IntStream;

public class ResultView {

    private static final String SEPARATOR = "|";
    private static final String NAME = "NAME";
    private static final String BLANK = "   ";
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 11;

    private ResultView() {
    }

    public static void printInit(Name playerName) {
        printFrame();

        printName(playerName.value());
        IntStream.range(FIRST_NUMBER, LAST_NUMBER)
                .forEach(value -> System.out.print(String.format("%4s %s", BLANK, SEPARATOR)));
        System.out.println();
        System.out.println();
    }

    public static void printResult(final Name playerName, final Frames frames) {
        printFrame();

        printName(playerName.value());

        IntStream.range(FIRST_NUMBER, LAST_NUMBER)
                .mapToObj(number -> number > frames.resultsByCurrent().size() ? "" : frames.resultsByCurrent().get(number - 1))
                .forEach(value -> System.out.print(String.format("%4s %s", value, SEPARATOR)));
        System.out.println();
        System.out.println();
    }

    private static void printName(String playerName) {
        System.out.print(String.format("%s %4s %s", SEPARATOR, playerName, SEPARATOR));
    }

    private static void printFrame() {
        printName(NAME);
        IntStream.range(FIRST_NUMBER, LAST_NUMBER)
                .mapToObj(number -> String.format("%02d", number))
                .forEach(string -> System.out.print(
                        String.format("%4s %s", string, SEPARATOR)
                ));
        System.out.println();
    }

}
