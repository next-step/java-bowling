package bowling;


import bowling.domain.FrameNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FrameNumberTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Created() {
        assertThat(new FrameNumber(1)).isEqualTo(new FrameNumber(1));
    }

    @Test
    @DisplayName("Increase 테스트")
    void When_Increase_Then_Increased() {
        assertThat(new FrameNumber(1).increase()).isEqualTo(new FrameNumber(2));
    }

    @Test
    @DisplayName("프레임번호가 10을넘어가면 Exception")
    void Given_InvalidFrameNumber_When_New_Then_Exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new FrameNumber(11))
                .withMessage("잘못된 프레임 번호");
    }
}
