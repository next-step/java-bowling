package bowling.domain;

import bowling.domain.PinCount.PinCount;
import bowling.domain.PinCount.PinCounts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinCountsTest {

    @Test
    void create() {
        PinCount firstPinCount = PinCount.of(3);
        PinCount secondPinCount = PinCount.of(4);
        PinCounts pinCounts = new PinCounts(Arrays.asList(firstPinCount, secondPinCount));

        assertThat(pinCounts.totalCount()).isEqualTo(firstPinCount.sumCount(secondPinCount));
    }

    @ParameterizedTest
    @CsvSource(value = {"5:4:false", "4:6:true", "2:5:false", "5:5:true", "0:9:false"}, delimiter = ':')
    void is_spare(String first, String second, boolean expectedResult) {
        PinCount firstPinCount = PinCount.of(first);
        PinCount secondPinCount = PinCount.of(second);
        PinCounts pinCounts = new PinCounts(Arrays.asList(firstPinCount, secondPinCount));

        assertThat(pinCounts.isSpare()).isEqualTo(expectedResult);
    }


    @ParameterizedTest
    @CsvSource(value = {"10:8", "8:8", "5:6", "7:10", "2:9"}, delimiter = ':')
    @DisplayName("두개의 pinCount가 max_total_count를 넘어갈때 예외 발생 테스트")
    void create_with_invalid_total_pin_counts(String first, String second) {
        PinCount firstPinCount = PinCount.of(first);
        PinCount secondPinCount = PinCount.of(second);

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCounts(Arrays.asList(firstPinCount, secondPinCount)));
    }
}
