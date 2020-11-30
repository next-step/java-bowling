package bowling.domain;

import bowling.exception.RollException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class RollTest {
    @ParameterizedTest
    @DisplayName("count 가 0부터 10의 숫자가 아니면, BadCountOfPinsException 이 발생한다.")
    @ValueSource(ints = {-1, 11})
    void constructor(int count) {
        assertThatExceptionOfType(RollException.class)
                .isThrownBy(() -> Roll.of(count));
    }

    @ParameterizedTest
    @DisplayName("count 가 같다면, of 가 리턴하는 Roll 도 같아야 한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void of(int count) {
        assertThat(Roll.of(count))
                .isEqualTo(Roll.of(count));
    }

    @ParameterizedTest
    @DisplayName("exportRollDto 테스트")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void exportRollDto(int count) {
        assertThat(Roll.of(count).exportRollDto().getCountOfPins())
                .isEqualTo(count);
    }

    @Test
    @DisplayName("sum 테스트")
    void sum() {
        List<Integer> counts = Arrays.asList(3, 5, 7);

        List<Roll> rolls = counts.stream().map(Roll::of).collect(toList());
        assertThat(Roll.sum(rolls))
                .isEqualTo(counts.stream()
                        .reduce(0, Integer::sum));
    }
}
