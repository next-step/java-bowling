package bowling.view;

import bowling.domain.Name;
import bowling.domain.frame.Frames;

import java.util.stream.IntStream;

public class ResultView {

    private static final String SEPARATOR = "|";
    private static final String TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BLANK = "     ";
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 11;

    private ResultView() {
    }

    public static void printInit(Name playerName) {
        System.out.println(TITLE);
        printName(playerName.value());
        IntStream.range(FIRST_NUMBER, LAST_NUMBER)
                .forEach(value -> System.out.print(String.format("%5s %s", BLANK, SEPARATOR)));
        System.out.println();
        System.out.println();
    }

    public static void printResult(final Name playerName, final Frames frames) {
        System.out.println(TITLE);
        printName(playerName.value());

        IntStream.range(FIRST_NUMBER, LAST_NUMBER)
                .mapToObj(number -> number > frames.resultsByCurrent().size() ? "" : frames.resultsByCurrent().get(number - 1))
                .forEach(value -> System.out.print(String.format("%5s %s", value, SEPARATOR)));
        System.out.println();
        System.out.println();
    }

    private static void printName(String playerName) {
        System.out.print(String.format("%s %4s %s", SEPARATOR, playerName, SEPARATOR));
    }

}
