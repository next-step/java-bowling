package bowling.view.output;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramePresenterTest {
    @DisplayName("마지막 프레임: strike -> spare")
    @Test
    void final_frame_strike_spare() {
        // given
        final FinalFrame finalFrame = FinalFrame.of(9);
        finalFrame.pitch(Pins.MAX);
        finalFrame.pitch(Pins.of(9));
        finalFrame.pitch(Pins.of(1));

        // when
        final String actual = FinalFramePresenter.present(finalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(finalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(finalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(finalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(finalFrame.getAllFallenPin());

        // then
        assertThat(actual).isEqualTo("-9");
    }

    @DisplayName("노멀 프레임: all gutter")
    @Test
    void normal_frame_all_gutter() {
        // given
        final NormalFrame normalFrame = NormalFrame.of(0);
        normalFrame.pitch(Pins.of(0));
        normalFrame.pitch(Pins.of(0));

        // when
        final String actual = FinalFramePresenter.present(normalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(normalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(normalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(normalFrame.getAllFallenPin());

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
        final String actual = FinalFramePresenter.present(normalFrame.getAllFallenPin());

        // then
        assertThat(actual).isEqualTo("X");
    }
}