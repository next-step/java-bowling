package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    @DisplayName("1에서 9사이의 frame만 생성 가능하다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void normal_frame(final int number) {

        assertThatThrownBy(() -> new NormalFrame(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("사이의 숫자만 가능합니다.");
    }

    @DisplayName("노멀 프레임에서 스패어 처리를 한다.")
    @Test
    void create_frame() {

        final NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.pitch(1);
        normalFrame.pitch(9);

        assertAll(
                () -> assertThat(normalFrame.isEmpty()).isFalse(),
                () -> assertThat(normalFrame.canPitch()).isFalse(),
                () -> assertThat(normalFrame.status()).isEqualTo(ScoreType.SPARE),
                () -> assertThat(normalFrame.pinsSize()).isEqualTo(2)
        );
    }

    @DisplayName("다음 프레임를 생성한다.")
    @Test
    void next_frame() {

        final NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.pitch(1);
        normalFrame.pitch(9);
        final Frame nextFrame = normalFrame.nextFrame();

        assertAll(
                () -> assertThat(nextFrame.isEmpty()).isTrue(),
                () -> assertThat(nextFrame.canPitch()).isTrue(),
                () -> assertThat(nextFrame.pinsSize()).isEqualTo(0)
        );
    }
}
