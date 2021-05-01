package bowling.domain;

public class NumberOfPlayers {

    private final int value;

    public NumberOfPlayers(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("최소 한 명 이상의 플레이어가 있어야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
