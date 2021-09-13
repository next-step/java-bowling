package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    @DisplayName("처음 투구 상태는 스트라이크 또는 넘버다.")
    @Test
    void first() {
        assertThat(Status.first(10)).isEqualTo(Status.STRIKE);
        assertThat(Status.first(9)).isEqualTo(Status.NUMBER);
    }

    @DisplayName("두번째 투구 - strike : 현재 투구가 10이고, 이전 투구 상태가 스트라이크 또는 스페어 이면 스트라이크다.")
    @Test
    void second_strike() {
        assertThat(Status.second(new Pitch(10, Status.STRIKE), 10)).isEqualTo(Status.STRIKE);
        assertThat(Status.second(new Pitch(10, Status.SPARE), 10)).isEqualTo(Status.STRIKE);
    }

    @DisplayName("두번째 투구 - spare : 현재 투구와 이전 투구 합이 10이고, 이전 투구 상태가 넘버이면 스페어다.")
    @ParameterizedTest
    @CsvSource(value = {"2:8", "3:7", "0:10", "0:10", "1:9"}, delimiter = ':')
    void second_spare(int first, int second) {
        assertThat(Status.second(new Pitch(first, Status.NUMBER), second)).isEqualTo(Status.SPARE);
    }

    @DisplayName("두번째 투구 - gutter : 현재 투구가 0 이고 , 이전 투구 상태가 넘버이면 거터다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void second_gutter(int first) {
        assertThat(Status.second(new Pitch(first, Status.NUMBER), 0)).isEqualTo(Status.GUTTER);
    }

    @DisplayName("두번째 투구 - number(miss) : 현재 투구와 이전 투구 합이 10이 아니고, 이전 투구 상태가 넘버이면 넘버다.")
    @ParameterizedTest
    @CsvSource(value = {"2:2", "0:1", "1:4", "2:4", "1:8"}, delimiter = ':')
    void second_number(int first, int second) {
        assertThat(Status.second(new Pitch(first, Status.NUMBER), second)).isEqualTo(Status.NUMBER);
    }
}