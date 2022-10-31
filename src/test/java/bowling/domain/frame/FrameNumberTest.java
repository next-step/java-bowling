package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameNumberTest {

    @DisplayName("프레임번호를 1 증가시킨다")
    @Test
    void increase() {
        assertThat(new FrameNumber(1).increase()).isEqualTo(2);
    }

    @DisplayName("다음이 최종프레임인지 여부를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {
            "8,false",
            "9,true"
    })
    void isNextFinal(int frameNumber, boolean expected) {
        assertThat(new FrameNumber(frameNumber).isNextFinal()).isEqualTo(expected);
    }
}
