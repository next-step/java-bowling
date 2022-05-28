package bowling.domain;

public class Spare extends EndedState {
    private static final String SPARE_SYMBOL = "/";

    private final Pitching first;
    private final Pitching second;

    public Spare(Pitching first, Pitching second) {
        validateSpareState(first, second);
        this.first = first;
        this.second = second;
    }

    private void validateSpareState(Pitching first, Pitching second) {
        if (!first.isSpare(second)) {
            throw new IllegalArgumentException(
                    String.format("스페어 상태가 아닙니다.(첫 번째 투구: %d, 두 번째 투구: %d)", first.getValue(), second.getValue()));
        }
    }

    @Override
    public String symbol() {
        return String.format("%s|%s", first.getValue(), SPARE_SYMBOL);
    }
}
