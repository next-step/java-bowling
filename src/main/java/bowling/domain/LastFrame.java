package bowling.domain;

import java.util.Collections;
import java.util.List;

public final class LastFrame extends DefaultFrame {
    private static final String FIRST_STRIKE_OR_SECOND_SPARE_MESSAGE =
            "세 번째 투구를 진행하기 위해서는 첫 번째 투구가 스트라이크이거나 두 번째 투구가 스페어야 합니다.";

    private LastFrame(final List<Pitch> pitches) {
        super(pitches);
    }

    public static DefaultFrame init() {
        return new LastFrame(Collections.emptyList());
    }

    @Override
    public boolean playing() {
        return !isPitchesFull() && (pitches().size() < maxPitchesCount() - 1 || isFirstStrike() || isSecondSpare());
    }

    @Override
    public DefaultFrame play(final int knockedPinsCount) {
        return play(KnockedPins.from(knockedPinsCount));
    }

    @Override
    public DefaultFrame play(final KnockedPins knockedPins) {
        validatePitchesFull();
        validateFirstStrikeOrSecondSpare();

        return new LastFrame(playedPitches(knockedPins));
    }

    private void validateFirstStrikeOrSecondSpare() {
        if (pitches().size() == maxPitchesCount() - 1 && !isFirstStrike() && !isSecondSpare()) {
            throw new IllegalArgumentException(FIRST_STRIKE_OR_SECOND_SPARE_MESSAGE);
        }
    }

    @Override
    public DefaultFrame next() {
        throw new IllegalArgumentException();
    }

    @Override
    public DefaultFrame last() {
        throw new IllegalArgumentException();
    }

    @Override
    protected int maxPitchesCount() {
        return 3;
    }
}
