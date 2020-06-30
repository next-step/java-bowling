package bowling.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class StatusTest {

    @Test
    @DisplayName("점수 상태를 반환")
    void makeStatus() {
        assertAll(() -> {
            assertThat(Status.makeStatus(10) instanceof Strike).isTrue();
            assertThat(Status.makeStatus(2, 8) instanceof Spare).isTrue();
            assertThat(Status.makeStatus(3, 6) instanceof Miss).isTrue();
        });

    }

    @Test
    @DisplayName("첫번째 투구의 결과를 출력한다")
    void printResult() {
        Status strike = Status.makeStatus(10);
        Status spare = Status.makeStatus(2, 8);
        Status miss = Status.makeStatus(3, 6);
        assertAll(() -> {
            assertThat(strike.printResult()).isEqualTo("X");
            assertThat(spare.printResult()).isEqualTo("2");
            assertThat(miss.printResult()).isEqualTo("3");
        });

    }

    @Test
    @DisplayName("전체 투구 결과를 출력한다")
    void printAllResult() {
        Status strike = Status.makeStatus(10);
        Status spare = Status.makeStatus(2, 8);
        Status miss = Status.makeStatus(3, 6);
        assertAll(() -> {
            assertThat(strike.printAllResult()).isEqualTo("X");
            assertThat(spare.printAllResult()).isEqualTo("2|/");
            assertThat(miss.printAllResult()).isEqualTo("3|6");
        });
    }

    @ParameterizedTest
    @DisplayName("쓰러진 핀 개 수가 0인 경우 - 을 반환, 아닐 경우 입력 값을 문자로 반환한다")
    @CsvSource(value = {"0, -", "7, 7"})
    void isGutter(int downPin, String result) {
        Status status = new Miss(downPin);
        assertThat(status.isGutter(downPin)).isEqualTo(result);
    }
}
