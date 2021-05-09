package bowling.domain;

import java.util.Collections;
import java.util.List;

public final class NormalFrame extends Frame {
    private static final String FIRST_STRIKE_MESSAGE = "첫 번재 투구가 스트라이크인 경우, 두 번째 투구를 진행할 수 없습니다.";

    private NormalFrame(final List<Pitch> pitches) {
        super(pitches);
    }

    public static Frame init() {
        return new NormalFrame(Collections.emptyList());
    }

    @Override
    public boolean playing() {
        return !isPitchesFull() && !isFirstStrike();
    }

    @Override
    public Frame play(final KnockedPins knockedPins) {
        validatePitchesFull();
        validateFirstStrike();

        return new NormalFrame(playedPitches(knockedPins));
    }

    private void validateFirstStrike() {
        if (isFirstStrike()) {
            throw new IllegalArgumentException(FIRST_STRIKE_MESSAGE);
        }
    }

    @Override
    public Frame next() {
        return NormalFrame.init();
    }

    @Override
    public Frame last() {
        return LastFrame.init();
    }

    @Override
    protected int maxPitchesCount() {
        return 2;
    }
}
