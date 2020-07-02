package bowling.view;

import bowling.domain.game.Bowling;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static void printHeader() {
        String header
                = "| NAME |" +
                IntStream.range(1, 11)
                        .boxed()
                        .map(index -> "  " + String.format("%02d", index) + "  |")
                        .collect(Collectors.joining());

        System.out.println(header);
    }

    public static void printBoard(Bowling bowling) {
        printHeader();
        System.out.println(bowling.toString());
    }
}
