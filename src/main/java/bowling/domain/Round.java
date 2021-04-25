package bowling.domain;

import java.util.Objects;

public class Round {
    public static final int BOWLING_MAX_ROUND = 10;
    public static final int BOWLING_MIN_ROUND = 1;

    private final int round;

    public Round(final int round) {
        this.round = round;
    }

    public static Round from(final int round) {
        if (round > BOWLING_MAX_ROUND) {
            throw new IllegalArgumentException("볼링 게임은 최대 10라운드 입니다.");
        }
        if (round < BOWLING_MIN_ROUND) {
            throw new IllegalArgumentException("볼링 게임은 최소 1라운드 입니다.");
        }
        return new Round(round);
    }

    public Round next() {
        return new Round(this.round + 1);
    }

    public int getRound() {
        return round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round1 = (Round) o;
        return round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }
}
