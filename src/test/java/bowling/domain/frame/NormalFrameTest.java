package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class NormalFrameTest {
    @DisplayName("10개 미만의 핀이 쓰러진 경우 다음 투구 가능")
    @Test
    void should_return_pitchable_true_when_first_fallen_pin_below_than_10() {
        // given
        final Frame normalFrame = NormalFrame.createFirst();
        final Pins pins = Pins.of(9);

        // when
        normalFrame.pitch(pins);

        // then
        assertThat(normalFrame.isPlayable()).isTrue();
    }

    @DisplayName("10개의 핀이 쓰러진 경우 다음 투구 불가")
    @Test
    void should_return_pitchable_false_when_first_fallen_pin_10() {
        // given
        final Frame normalFrame = NormalFrame.createFirst();

        // when
        normalFrame.pitch(Pins.MAX);

        // then
        assertThat(normalFrame.isPlayable()).isFalse();
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

    @DisplayName("노멀 프레임: all gutter")
    @Test
    void normal_frame_all_gutter() {
        // given
        final NormalFrame normalFrame = NormalFrame.of(0);
        normalFrame.pitch(Pins.of(0));
        normalFrame.pitch(Pins.of(0));

        // when
        final String actual = normalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("--");
    }

    @DisplayName("노멀 프레임: miss")
    @Test
    void normal_frame_miss() {
        // given
        final NormalFrame normalFrame = NormalFrame.of(0);
        normalFrame.pitch(Pins.of(1));
        normalFrame.pitch(Pins.of(7));

        // when
        final String actual = normalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("17");
    }

    @DisplayName("노멀 프레임: gutter -> spare")
    @Test
    void normal_frame_gutter_spare() {
        // given
        final NormalFrame normalFrame = NormalFrame.of(0);
        normalFrame.pitch(Pins.of(0));
        normalFrame.pitch(Pins.MAX);

        // when
        final String actual = normalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("-/");
    }

    @DisplayName("노멀 프레임: spare")
    @Test
    void normal_frame_spare() {
        // given
        final NormalFrame normalFrame = NormalFrame.of(0);
        normalFrame.pitch(Pins.of(9));
        normalFrame.pitch(Pins.of(1));

        // when
        final String actual = normalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("9/");
    }

    @DisplayName("노멀 프레임: strike")
    @Test
    void normal_frame_spare_strike() {
        // given
        final NormalFrame normalFrame = NormalFrame.of(0);
        normalFrame.pitch(Pins.MAX);

        // when
        final String actual = normalFrame.getSymbol();

        // then
        assertThat(actual).isEqualTo("X");
    }
}
