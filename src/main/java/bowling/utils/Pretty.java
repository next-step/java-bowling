package bowling.utils;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class Pretty {

    private static final int DEFAULT_SPACE = 8;
    private static final String EMPTY = " ";

    public static String alignCenter(String... targets) {
        String merge = Arrays.stream(targets)
                .collect(joining("|"));
        return alignCenter(merge);
    }

    public static String alignCenter(String target) {
        int half = DEFAULT_SPACE - target.length();
        int padding = half / 2;

        String empty = IntStream.range(0, padding)
                .mapToObj(num -> EMPTY)
                .collect(joining());

        String after = empty.concat(target).concat(empty);
        return (after.length() < DEFAULT_SPACE) ? after + EMPTY : after;
    }
}
