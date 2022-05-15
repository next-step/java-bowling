package bowling.model.frame.state;

import java.util.Arrays;

enum CountToMarkConverter {

    TEN(10, "x"),
    ZERO(0, "-");

    private final int count;
    private final String mark;

    CountToMarkConverter(int count, String mark) {
        this.count = count;
        this.mark = mark;
    }

    static String convert(int count) {
        return Arrays.stream(values()).filter(value -> value.count == count)
                .findAny()
                .map(value -> value.mark)
                .orElse(String.valueOf(count));
    }
}
