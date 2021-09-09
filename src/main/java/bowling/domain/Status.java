package bowling.domain;

import java.util.Arrays;

public enum Status {
    STRIKE(" X ", 10),
    SPARE(" / ", 10),
    GUTTER(" - ", 0),
    MISS("0", 0);

    private final String letter;
    private final int score;

    Status(String letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public static Status find(int score, boolean spare) {
        Status bowlingStatus = Arrays.stream(Status.values())
            .filter(status -> status.score == score)
            .findAny()
            .orElse(Status.MISS);

        // 스페어인 경우
        if (SPARE.equals(bowlingStatus) && spare) {
            return SPARE;
        }

        return bowlingStatus;
    }

    public String getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }
}
