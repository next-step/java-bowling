package bowling;

import java.util.stream.Stream;

public enum Score {

    STRIKE(10, "X", true),
    SPARE(10, "/", false),
    MISS(-1, "", false),
    GUTTER(0, "-", false);

    private int count;
    private String type;
    private boolean first;

    Score(int count, String type, boolean first) {
        this.count = count;
        this.type = type;
        this.first = first;
    }

    public boolean isFirst() {
        return first;
    }

    public static Score of(int pinCount, boolean first) {
        Score[] values = values();
        return Stream.of(values)
                .filter(score -> score.count == pinCount && score.isFirst() == first)
                .findFirst()
                .orElse(MISS);
    }
}
