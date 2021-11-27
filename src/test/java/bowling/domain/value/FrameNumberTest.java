package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("프레임 테스트")
class FrameNumberTest {
    @DisplayName("정상적으로 프레임 생성")
    @RepeatedTest(10)
    void create(RepetitionInfo repetitionInfo) {
        int number = repetitionInfo.getCurrentRepetition();
        assertDoesNotThrow(
                () -> FrameNumber.from(number));
    }

    @Test
    @DisplayName("잘못된 프레임 생성 예외 검증")
    void create_exception() {
        assertAll(() -> {
            assertThatIllegalArgumentException().isThrownBy(() -> FrameNumber.from(-1));
            assertThatIllegalArgumentException().isThrownBy(() -> FrameNumber.from(11));
        });
    }
}
