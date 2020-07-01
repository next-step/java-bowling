package bowling.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class StatusTest {

    @Test
    @DisplayName("상태 별 투구 결과를 출력")
    void printResult() {
        assertAll(() -> {
            assertThat(new Strike().printResult()).isEqualTo("X");
            assertThat(new Pending(5).printResult()).isEqualTo("5");
            assertThat(new Spare(2, 8).printResult()).isEqualTo("2|/");
            assertThat(new Miss(2, 4).printResult()).isEqualTo("2|4");
        });
    }


    @ParameterizedTest
    @DisplayName("쓰러진 핀 개 수가 0인 경우 - 을 반환, 아닐 경우 입력 값을 문자로 반환한다")
    @CsvSource(value = {"0, -", "7, 7"})
    void isGutter(int downPin, String result) {
        Status status = new Pending(downPin);
        assertThat(status.isGutter(downPin)).isEqualTo(result);
    }
}
