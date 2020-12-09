package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @DisplayName("10개 미만의 핀이 쓰러진 경우 다음 투구 가능")
    @Test
    void should_return_pitchable_true_when_first_fallen_pin_below_than_10() {
        // given
        final Frame normalFrame = NormalFrame.first();
        final Pins pins = Pins.of(9);

        // when
        normalFrame.pitch(pins);

        // then
        assertThat(normalFrame.isPitchable()).isTrue();
    }

    @DisplayName("10개의 핀이 쓰러진 경우 다음 투구 불가")
    @Test
    void should_return_pitchable_false_when_first_fallen_pin_10() {
        // given
        final Frame normalFrame = NormalFrame.first();
        final Pins pins = Pins.MAX;

        // when
        normalFrame.pitch(pins);

        // then
        assertThat(normalFrame.isPitchable()).isFalse();
    }

}
