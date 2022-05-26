package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameNumberTest {
    @DisplayName("프레임 넘버를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 8, 9})
    void FrameNumber_생성(int frameNumber) {
        assertThat(new FrameNumber(frameNumber)).isNotNull().isInstanceOf(FrameNumber.class);
    }

    @DisplayName("프레임 넘버가 0 ~ 9 범위를 벗어날 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 10, 11})
    void FrameNumber_생성_예외(int frameNumber) {
        assertThatThrownBy(() -> new FrameNumber(frameNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다음 프레임 넘버를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, 1",
            "1, 2",
            "7, 8",
            "8, 9"
    })
    void FrameNumber_다음_프레임_넘버_반환(int currentNumber, int nextNumber) {
        FrameNumber frameNumber = new FrameNumber(currentNumber);
        FrameNumber nextFrameNumber = frameNumber.next();
        assertThat(nextFrameNumber).isEqualTo(new FrameNumber(nextNumber));
    }

    @DisplayName("다음 프레임 넘버가 최대 프레임 넘버를 넘어가면 예외가 발생한다.")
    @Test
    void FrameNumber_다음_프레임_넘버_반환_예외() {
        FrameNumber frameNumber = new FrameNumber(9);
        assertThatThrownBy(() -> frameNumber.next()).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("현재 프레임 넘버가 마지막 프레임 넘버 인지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "0, false",
            "1, false",
            "8, false",
            "9, true"
    })
    void FrameNumber_마지막_프레임_넘버_체크(int number, boolean trueOrFalse) {
        FrameNumber frameNumber = new FrameNumber(number);
        assertThat(frameNumber.isLast()).isEqualTo(trueOrFalse);
    }
}