package bowling.model.player;

import java.util.Objects;

public class PlayerNumber {
    private static final int MIN = 1;

    private int number;

    public PlayerNumber(int number) {
        validateRange(number);

        this.number = number;
    }

    private void validateRange(int number) {
        if (number < MIN) {
            throw new IllegalArgumentException(String.format("플레이어 번호는 최소 %d 이상이어야 합니다.", MIN));
        }
    }

    public int value() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerNumber)) return false;
        PlayerNumber that = (PlayerNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
