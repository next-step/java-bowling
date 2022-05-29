package bowling.domain;

public class Miss extends EndedState {
    private static final String FRAME_STATE_FORMAT = "%s|%s";
    private static final String GUTTER_SYMBOL = "-";

    private final Pitching first;
    private final Pitching second;

    public Miss(Pitching first, Pitching second) {
        validate(first, second);
        this.first = first;
        this.second = second;
    }

    private void validate(Pitching first, Pitching second) {
        if (!first.isMiss(second)) {
            throw new IllegalArgumentException(
                    String.format("첫 번째 투구 %s, 두 번째 투구 %s 인 경우, miss 상태가 아닙니다.", first.getValue(), second.getValue()));
        }
    }

    @Override
    public String symbol() {
        return String.format(FRAME_STATE_FORMAT, formatValue(first), formatValue(second));
    }

    @Override
    public Score score() {
        return first.score(second);
    }

    @Override
    public Score calculatorScore(Score before) {
        before = before.bowl(first.score());

        if(before.isCalculatorScore()) {
            return before;
        }

        return before.bowl(second.score());
    }

    private String formatValue(Pitching pitching) {
        if (pitching.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(pitching.getValue());
    }
}
