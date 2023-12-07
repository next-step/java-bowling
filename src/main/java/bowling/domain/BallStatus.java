package bowling.domain;

public enum BallStatus {
    STIKE("X"), GUTTER("-"), SPARE("/"), OPEN(""), NOTHING(" ");

    private final String mark;

    BallStatus(String mark) {
        this.mark = mark;
    }

    public static BallStatus from(int ball) {
        if (ball == 10) {
            return STIKE;
        }
        if (ball == 0) {
            return GUTTER;
        }
        return OPEN;
    }

    public boolean isStrike() {
        return this == STIKE;
    }

    public String getMark() {
        return mark;
    }
}
