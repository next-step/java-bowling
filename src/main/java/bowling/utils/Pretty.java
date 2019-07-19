package bowling.utils;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class Pretty {

    public static final String EMPTY = " ";
    public static final String PARTITION = "|";
    public static final String PARTITION_OF_SYMBOL = "|";

    private static final int DEFAULT_SPACE_SIZE = 8;
    private static final int BISECTION = 2;
    private static final int DEFAULT_SIZE_OF_PADDING = 0;

    public static String putPartitionOfState(String... targets) {
        return Arrays.stream(targets)
                .collect(joining(PARTITION_OF_SYMBOL));
    }

    public static String alignCenter(String target) {
        int remainingSpace = DEFAULT_SPACE_SIZE - target.length();
        int sizeOfPadding = remainingSpace / BISECTION;

        String padding = IntStream.range(DEFAULT_SIZE_OF_PADDING, sizeOfPadding)
                .mapToObj(num -> EMPTY)
                .collect(joining());

        String afterTarget = padding + target + padding;
        if (DEFAULT_SPACE_SIZE > afterTarget.length()) {
            return afterTarget + EMPTY;
        }
        return afterTarget;
    }
}
