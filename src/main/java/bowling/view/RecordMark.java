package bowling.view;

import java.util.Arrays;

public enum RecordMark {
    GUTTER(0, "-"),
    STRIKE(10, "X");

    private final int record;
    private final String mark;

    RecordMark(int record, String mark) {
        this.record = record;
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public static String find(int number) {
        return Arrays.stream(values())
                .filter(key -> key.record == number)
                .findFirst()
                .map(mark -> mark.mark)
                .orElse(String.valueOf(number));
    }
}
