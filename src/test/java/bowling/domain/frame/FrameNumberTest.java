package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameNumberTest {
    static final FrameNumber MIN_FRAME_NUMBER = FrameNumber.min();
    static final FrameNumber MAX_FRAME_NUMBER = new FrameNumber(FrameNumber.MAX);

    @ParameterizedTest
    @ValueSource(ints = {FrameNumber.MIN - 1, FrameNumber.MAX + 1})
    void FrameNumber는_범위_밖_값으로_생성_될_경우_예외를_발생_시킨다(int frameNumber) {
        assertThatThrownBy(() -> {
            new FrameNumber(frameNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isFinal은_마지막_여부를_반환한다() {
        assertAll(
                () -> assertFalse(MIN_FRAME_NUMBER.isFinal()),
                () -> assertTrue(MAX_FRAME_NUMBER.isFinal())
        );
    }

    @Test
    void isNormal은_일반_frameNumber_여부를_반환한다() {
        assertAll(
                () -> assertFalse(new FrameNumber(FrameNumber.MAX_IN_NORMAL_FRAME + 1).isNormal()),
                () -> assertTrue(new FrameNumber(FrameNumber.MAX_IN_NORMAL_FRAME).isNormal()),
                () -> assertTrue(new FrameNumber(FrameNumber.MIN).isNormal())
        );
    }

    @Test
    void isMaxInNormal은_마지막_일반_franeNumber_여부를_반환한다() {
        assertAll(
                () -> assertFalse(new FrameNumber(FrameNumber.MAX_IN_NORMAL_FRAME + 1).isMaxInNormal()),
                () -> assertTrue(new FrameNumber(FrameNumber.MAX_IN_NORMAL_FRAME).isMaxInNormal()),
                () -> assertFalse(new FrameNumber(FrameNumber.MAX_IN_NORMAL_FRAME - 1).isMaxInNormal())
        );
    }

    @Test
    void next는_다음_FrameNumber를_반환한다() {
        FrameNumber next = MIN_FRAME_NUMBER.next();

        assertThat(next).isInstanceOf(FrameNumber.class);
    }
}
