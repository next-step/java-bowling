package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FrameNumberTest {
    @Test
    @DisplayName("프레임번호 객체 생성을 확인한다")
    void checkedFrameNumberObjectGenerate() {
        // given
        int value = 5;

        // when
        FrameNumber frameNumber = new FrameNumber(value);

        // then
        assertThat(frameNumber.getValue()).isEqualTo(value);
    }

    @Test
    @DisplayName("첫 번째 프레임을 생성한다")
    void checkedFrameNumberIsFirst() {
        // given & when
        FrameNumber frameNumber = FrameNumber.first();

        // then
        assertThat(frameNumber.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("마지막 번째 프레임을 생성한다")
    void checkedFrameNumberIsLast() {
        // given & when
        FrameNumber frameNumber = FrameNumber.last();

        // then
        assertThat(frameNumber.getValue()).isEqualTo(10);
    }

    @Test
    @DisplayName("다음 프레임을 생성한다")
    void checkedNextFrameNumberGenerate() {
        // given
        FrameNumber frameNumber = new FrameNumber(5);

        // when
        FrameNumber nextFrameNumber = frameNumber.next();

        // then
        assertThat(nextFrameNumber.getValue()).isEqualTo(frameNumber.getValue() + 1);
    }

    @ParameterizedTest(name = "프레임 번호가 1에서 10사이의 범위를 벗어나는 {0}인 경우 예외처리를 한다.")
    @ValueSource(ints = {-1, 11})
    void exceptionFrameNumberOverStandardRange(int value) {
        // when & then
        assertThatThrownBy(() -> new FrameNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("프레임이 마지막 프레임인지 확인힌다.")
    void checkedFrameNumberIsMax() {
        // given
        FrameNumber frameNumber = new FrameNumber(10);

        // then
        assertThat(frameNumber.isMax()).isTrue();
    }
}
