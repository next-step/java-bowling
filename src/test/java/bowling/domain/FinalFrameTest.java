package bowling.domain;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FinalFrameTest {
    @DisplayName("10개 미만의 핀이 쓰러진 경우 다음 투구 가능")
    @Test
    void should_return_pitchable_true_when_first_fallen_pin_below_than_10() {
        // given
        final Frame finalFrame = FrameFactoryTest.FINAL;
        final Pins pins = Pins.of(9);

        // when
        finalFrame.pitch(pins);

        // then
        assertThat(finalFrame.isPitchable()).isTrue();
    }

    @DisplayName("10개의 핀이 쓰러진 경우 보너스 투구 가능")
    @Test
    void should_return_pitchable_true_when_first_fallen_pin_10() {
        // given
        final Frame normalFrame = FrameFactoryTest.FINAL;

        // when
        normalFrame.pitch(Pins.MAX);

        // then
        assertThat(normalFrame.isPitchable()).isTrue();
    }

    @DisplayName("첫 번째, 두 번째 투구의 쓰러진 볼링 핀 합이 10개인 경우 보너스 투구 가능")
    @Test
    void should_return_pitchable_true_when_first_second_fallen_pin_sum_is_10() {
        // given
        final Frame normalFrame = FrameFactoryTest.FINAL;
        final Pins first = Pins.of(5);
        final Pins second = Pins.of(5);

        // when
        normalFrame.pitch(first);
        normalFrame.pitch(second);

        // then
        assertThat(normalFrame.isPitchable()).isTrue();
    }

    @DisplayName("첫 번째, 두 번째 투구의 쓰러진 볼링 핀 합이 10개가 넘는 경우")
    @Test
    void should_throw_exception_when_first_second_fallen_pin_sum_is_grater_than_10() {
        // given
        final Frame normalFrame = NormalFrame.createFirst();
        final Pins first = Pins.of(5);
        final Pins second = Pins.of(6);

        // when
        normalFrame.pitch(first);
        final Throwable thrown = catchThrowable(() -> normalFrame.pitch(second));

        // then
        AssertionsForClassTypes.assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
