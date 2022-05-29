package bowling.view;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class OutputVIew {

    private static final String FIRST_COLUMN = "| %s |";
    private static final String OTHER_COLUMN = " %s |";
    private static final String BLANK = "";
    private static final String SPACE_BLANK = " ";
    private static final String ZERO_STRING = "0";
    private static final String NAME = "NAME";

    private OutputVIew() {
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void printGameInfo(Player player) {
        printIndex();

        AtomicReference<String> message = new AtomicReference<>(String.format(FIRST_COLUMN, player.name()));
        IntFunction<String> columnFunc = i -> SPACE_BLANK;

        System.out.println(createTable(message, columnFunc));
    }

    public static void printGameResult(Player player, Frames frames) {
        printIndex();

        AtomicReference<String> message = new AtomicReference<>(String.format(FIRST_COLUMN, player.name()));
        IntFunction<String> func = i -> {
            int lastRound = frames.lastRound();
            if (lastRound < i || lastRound <= -1) {
                return BLANK;
            }
            return frames.getFrame(i).partitionPins();
        };

        System.out.println(createTable(message, func) + System.lineSeparator());
    }

    private static void printIndex() {
        AtomicReference<String> message = new AtomicReference<>(String.format(FIRST_COLUMN, NAME));
        IntFunction<String> indexFunc = i -> {
            int index = i + 1;
            if (i == 9) {
                return String.valueOf(index);
            }
            return ZERO_STRING + index;
        };
        System.out.println(createTable(message, indexFunc));
    }

    private static String createTable(AtomicReference<String> message, IntFunction<String> func) {
        IntStream.range(0, 10)
                .mapToObj(func)
                .forEach(i -> message.getAndAccumulate(String.format(OTHER_COLUMN, i), (s, s2) -> s + s2));

        return message.get();
    }
}
