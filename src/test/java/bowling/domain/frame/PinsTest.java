package bowling.domain.frame;

import bowling.dto.PinDto;
import bowling.exception.PinsOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PinsTest {

    private final List<Pin> pinList = IntStream.rangeClosed(0, 10)
            .mapToObj(Pin::of)
            .collect(toList());
    private Pins pins;

    @BeforeEach
    void setUp() {
        pins = new Pins();
        pinList.forEach(pins::add);
    }

    @Test
    @DisplayName("size 테스트")
    void size() {
        assertThat(pins.size())
                .isEqualTo(11);
    }

    @ParameterizedTest
    @DisplayName("sum 테스트")
    @CsvSource(value = {"1$5", "0$3", "6$1"}, delimiter = '$')
    void sum(int startIdx, int offset) {
        assertThat(pins.sum(startIdx, offset))
                .isEqualTo(IntStream.range(startIdx, startIdx + offset)
                        .reduce(0, Integer::sum));
    }

    @ParameterizedTest
    @DisplayName("sum 에서 index 의 범위가 벗어나면, PinsOutOfRangeException 이 발생한다.")
    @CsvSource(value = {"-1$5", "0$-3", "100$1000"}, delimiter = '$')
    void sum_exception(int startIdx, int offset) {
        assertThatExceptionOfType(PinsOutOfRangeException.class)
                .isThrownBy(() -> pins.sum(startIdx, offset))
                .withMessage("pins 의 범위를 벗어난 index 입니다.");
    }

    @Test
    @DisplayName("exportPinsDto 테스트")
    void exportPinsDto() {
        assertThat(pins.exportPinsDto()
                .getPins()
                .stream()
                .map(PinDto::getCountOfPins)
                .collect(toList())
        ).isEqualTo(IntStream.rangeClosed(0, 10)
                .boxed()
                .collect(toList()));
    }
}
