package step4.domain;

import java.util.Objects;

public class NumberOfPlayer {
    private static final String CHECK_NUMBER_OF_PLAYER_BOUND = "플레이어의 수는 0보다 커야합니다.";
    private static final int BOUND_MIN = 1;
    private final int numberOfPlayer;

    public NumberOfPlayer(int numberOfPlayer) {
        checkBound(numberOfPlayer);
        this.numberOfPlayer = numberOfPlayer;
    }

    private void checkBound(int numberOfPlayer) {
        if (numberOfPlayer < BOUND_MIN) {
            throw new IllegalArgumentException(CHECK_NUMBER_OF_PLAYER_BOUND);
        }
    }

    public NumberOfPlayer(String numberOfPlayer) {
        this(Integer.valueOf(numberOfPlayer));
    }

    public int numberOfPlayer() {
        return numberOfPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfPlayer that = (NumberOfPlayer) o;
        return numberOfPlayer == that.numberOfPlayer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPlayer);
    }
}