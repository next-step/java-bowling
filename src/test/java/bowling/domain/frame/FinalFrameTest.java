package bowling.domain.frame;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    
    @DisplayName("보너스 투구까지 완료된 경우 투구는 더 이상 진행 불가")
    @Test
    void should_return_pitchable_false_when_bonus_pitching_is_finished() {
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

    @DisplayName("마지막 프레임: strike -> spare")
    @Test
    void final_frame_strike_spare() {
        // given
        final FinalFrame finalFrame = FinalFrame.of(9);
        finalFrame.pitch(Pins.MAX);
        finalFrame.pitch(Pins.of(9));
        finalFrame.pitch(Pins.of(1));

        // when
        final String actual = finalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("X9/");
    }

    @DisplayName("마지막 프레임: strike -> gutter -> spare")
    @Test
    void final_frame_strike_gutter_spare() {
        // given
        final FinalFrame finalFrame = FinalFrame.of(9);
        finalFrame.pitch(Pins.MAX);
        finalFrame.pitch(Pins.of(0));
        finalFrame.pitch(Pins.MAX);

        // when
        final String actual = finalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("X-/");
    }

    @DisplayName("마지막 프레임: spare -> strike")
    @Test
    void final_frame_spare_strike() {
        // given
        final FinalFrame finalFrame = FinalFrame.of(9);
        finalFrame.pitch(Pins.of(1));
        finalFrame.pitch(Pins.of(9));
        finalFrame.pitch(Pins.MAX);

        // when
        final String actual = finalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("1/X");
    }

    @DisplayName("마지막 프레임: spare -> gutter")
    @Test
    void final_frame_spare_gutter() {
        // given
        final FinalFrame finalFrame = FinalFrame.of(9);
        finalFrame.pitch(Pins.of(0));
        finalFrame.pitch(Pins.MAX);
        finalFrame.pitch(Pins.of(0));

        // when
        final String actual = finalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("-/-");
    }

    @DisplayName("마지막 프레임: miss")
    @Test
    void final_frame_miss() {
        // given
        final FinalFrame finalFrame = FinalFrame.of(9);
        finalFrame.pitch(Pins.of(0));
        finalFrame.pitch(Pins.of(9));

        // when
        final String actual = finalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("-9");
    }
}
