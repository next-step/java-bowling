package bowling.domain.pins;

import bowling.domain.score.Score;
import java.util.Arrays;

public enum Status {
    STRIKE(" X ", Score.from(10)),
    SPARE(" / ", Score.from(10)),
    GUTTER(" - ", Score.create()),
    MISS("0", Score.create()),
    READY("READY", Score.create());

    private final String letter;
    private final Score score;

    Status(String letter, Score score) {
        this.letter = letter;
        this.score = score;
    }

    public static Status find(Score score, boolean spare) {
        Status bowlingStatus = Arrays.stream(Status.values())
            .filter(status -> status.score.equals(score))
            .findAny()
            .orElse(Status.MISS);

        if (bowlingStatus == STRIKE && spare) {
            return SPARE;
        }

        return bowlingStatus;
    }

    public static boolean isEnd(Status status) {
        return status == STRIKE || status == SPARE;
    }

    public String getLetter() {
        return letter;
    }


}
