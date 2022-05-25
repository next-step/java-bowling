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
    static final FrameNumber MIN = FrameNumber.min();
    static final FrameNumber MAX = new FrameNumber(FrameNumber.MAX);

    @ParameterizedTest
    @ValueSource(ints = {FrameNumber.MIN - 1, FrameNumber.MAX + 1})
    void FrameNumber는_범위_밖_값으로_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new FrameNumber(FrameNumber.MIN - 1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isLast는_마지막_여부를_반환한다() {
        assertAll(
                () -> assertFalse(MIN.isLast()),
                () -> assertTrue(MAX.isLast())
        );
    }

    @Test
    void next는_다음_FrameNumber를_반환한다() {
        FrameNumber next = MIN.next();

        assertThat(next).isInstanceOf(FrameNumber.class);
    }
}
