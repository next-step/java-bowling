package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("볼링핀 테스트")
class PinsTest {

    @DisplayName("정상적으로 핀 생성")
    @RepeatedTest(10)
    void create(RepetitionInfo repetitionInfo) {
        int number = repetitionInfo.getCurrentRepetition();
        assertDoesNotThrow(
                () -> Pins.from(number));
    }

    @Test
    @DisplayName("잘못된 핀 예외 검증")
    void create_exception() {
        assertAll(() -> {
            assertThatIllegalArgumentException().isThrownBy(() -> Pins.from(-1));
            assertThatIllegalArgumentException().isThrownBy(() -> Pins.from(11));
        });
    }
}

