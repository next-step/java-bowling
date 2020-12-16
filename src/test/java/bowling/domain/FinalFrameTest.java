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
        final Frame finalFrame = FrameFactoryTest.createFinal();
        final Pins pins = Pins.of(9);

        // when
        finalFrame.pitch(pins);

        // then
        assertThat(finalFrame.isPlayable()).isTrue();
    }

    @DisplayName("10개의 핀이 쓰러진 경우 보너스 투구 가능")
    @Test
    void should_return_pitchable_true_when_first_fallen_pin_10() {
        // given
        final Frame finalFrame = FrameFactoryTest.createFinal();

        // when
        finalFrame.pitch(Pins.MAX);

        // then
        assertThat(finalFrame.isPlayable()).isTrue();
    }

    @DisplayName("첫 번째, 두 번째 투구의 쓰러진 볼링 핀 합이 10개인 경우 보너스 투구 가능")
    @Test
    void should_return_pitchable_true_when_first_second_fallen_pin_sum_is_10() {
        // given
        final Frame finalFrame = FrameFactoryTest.createFinal();
        final Pins first = Pins.of(5);
        final Pins second = Pins.of(5);

        // when
        finalFrame.pitch(first);
        finalFrame.pitch(second);

        // then
        assertThat(finalFrame.isPlayable()).isTrue();
    }
    
    @DisplayName("")
    @Test
    void test() {
        // given
        final Frame finalFrame = FrameFactoryTest.createFinal();
        final Pins first = Pins.of(5);
        final Pins second = Pins.of(5);
        final Pins bonus = Pins.of(5);

        // when
        finalFrame.pitch(first);
        finalFrame.pitch(second);
        finalFrame.pitch(bonus);

        // then
        assertThat(finalFrame.isNotPlayable()).isTrue();
    }
}
