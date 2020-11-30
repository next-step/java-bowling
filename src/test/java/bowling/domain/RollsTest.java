package bowling.domain;

import bowling.dto.RollDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class RollsTest {

    private final List<Roll> rollList = IntStream.rangeClosed(0, 10)
            .mapToObj(Roll::of)
            .collect(toList());
    private Rolls rolls;

    @BeforeEach
    void setUp() {
        rolls = new Rolls();
        rollList.forEach(rolls::add);
    }

    @Test
    @DisplayName("size 테스트")
    void size() {
        assertThat(rolls.size())
                .isEqualTo(11);
    }

    @ParameterizedTest
    @DisplayName("sum 테스트")
    @CsvSource(value = {"1$5", "0$3", "6$1"}, delimiter = '$')
    void sum(int startIdx, int offset) {
        assertThat(rolls.sum(startIdx, offset))
                .isEqualTo(IntStream.range(startIdx, startIdx + offset)
                        .reduce(0, Integer::sum));
    }

    @Test
    @DisplayName("exportRollsDto 테스트")
    void exportRollsDto() {
        assertThat(rolls.exportRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList())
        ).isEqualTo(IntStream.rangeClosed(0, 10)
                .boxed()
                .collect(toList()));
    }
}
