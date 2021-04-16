package bowling.view;

import java.io.PrintStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ResultView {

    public static final String SPLIT_DELIMITER = "|";
    public static final String NAME = "NAME";
    public static final String BOWLING_NAME_FORMAT = " %-4s ";
    public static final String BOWLING_FRAME_COUNT_FORMAT = "  %-2s  ";
    public static final String SPACE_FORMAT = " ";
    public static final String PADDING_FORMAT = "%2d";
    public static final String PADDING_ZERO = "0";

    private PrintStream out = new PrintStream(System.out);

    // 프레임 점수판 출력
    public String printResult() {
        return Stream.concat(name(), frame())
                .collect(Collectors.joining(SPLIT_DELIMITER, SPLIT_DELIMITER, SPLIT_DELIMITER));
    }

    private Stream<String> name() {
        return Stream.of(format(BOWLING_NAME_FORMAT, NAME));
    }

    private Stream<String> frame() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(value -> format(BOWLING_FRAME_COUNT_FORMAT, paddingNumber(value)));
    }

    private String paddingNumber(int value) {
        return String.format(PADDING_FORMAT, value).replace(SPACE_FORMAT, PADDING_ZERO);
    }
    // 프레임 점수판 출력 end

    private String format(String format, String value) {
        return String.format(format, value);
    }
}
