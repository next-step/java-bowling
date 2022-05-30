package bowling.domain;

public class FirstPitching extends RunningState {
    private static final String GUTTER_SYMBOL = "-";

    private final Pitching first;

    public FirstPitching(Pitching first) {
        this.first = first;
    }

    @Override
    public State bowl(Pitching pitching) {
        validatePitchingSum(pitching);

        if (first.isSpare(pitching)) {
            return new Spare(first, pitching);
        }
        return new Miss(first, pitching);
    }

    private void validatePitchingSum(Pitching second) {
        if (!first.isSum(second)) {
            throw new IllegalArgumentException(
                    String.format("첫 번째 투구는 %s, 두 번째 투구는 %s입니다. 투구의 합은 %d를 넘을 수 없습니다.", first.getValue(), second.getValue(),
                            Pitching.MAX_PITCHING));
        }
    }

    @Override
    public String symbol() {
        if (first.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(first.getValue());
    }

    @Override
    public Score score() {
        return first.score();
    }

    @Override
    public Score calculatorScore(Score before) {
        return before.bowl(first.score());
    }

    public Pitching getFirst() {
        return first;
    }
}
