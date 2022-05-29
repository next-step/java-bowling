package bowling.view;

import bowling.domain.frame.Frames;
import bowling.domain.game.Game;
import bowling.domain.player.Player;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class OutputVIew {

    private static final String FIRST_COLUMN = "| %7s |";
    private static final String OTHER_COLUMN = " %7s |";
    private static final String NAME = "NAME";
    private static final String BLANK = "";
    private static final int ONE = 1;
    private static final int START_ROUND = 0;
    private static final int LAST_ROUND = 9;
    private static final int LIMITED_ROUND = 10;

    private OutputVIew() {
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void printGame(Game game) {
        printIndex();

        Player player = game.player();
        Frames frames = game.frames();
        AtomicReference<String> message = new AtomicReference<>(String.format(FIRST_COLUMN, player.name()));

        IntFunction<String> func = i -> {
            int lastRound = frames.lastRound();
            if (i <= lastRound && !frames.isEmpty()) {
                return frames.getFrame(i).partitionPins();
            }

            return BLANK;
        };

        System.out.println(createTable(message, func) + System.lineSeparator());
    }

    private static void printIndex() {
        AtomicReference<String> message = new AtomicReference<>(String.format(FIRST_COLUMN, NAME));
        IntFunction<String> indexFunc = i -> {
            int index = i + ONE;
            if (i == LAST_ROUND) {
                return String.valueOf(index);
            }
            return "" + START_ROUND + index;
        };
        System.out.println(createTable(message, indexFunc));
    }

    private static String createTable(AtomicReference<String> message, IntFunction<String> func) {
        IntStream.range(START_ROUND, LIMITED_ROUND)
                .mapToObj(func)
                .forEach(i -> message.getAndAccumulate(String.format(OTHER_COLUMN, i), (s, s2) -> s + s2));

        return message.get();
    }
}
