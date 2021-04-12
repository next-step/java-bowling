package bowling.domain;

import bowling.domain.State.Miss;
import bowling.domain.State.Spare;
import bowling.domain.frame.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class StateTest {

    @ParameterizedTest
    @CsvSource(value = {"1:10", "2:9", "7:7", "8:5", "5:7"}, delimiter = ':')
    @DisplayName("잘못된 pinCount로 miss생성시 예외 발생 테스트")
    void create_miss_with_invalid_pinCounts(String first, String second) {
        PinCount firstPinCount = new PinCount(first);
        PinCount secondPinCount = new PinCount(second);
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Miss(firstPinCount,secondPinCount));
    }

    @ParameterizedTest
    @CsvSource(value = {"1:10", "2:9", "7:7", "8:5", "5:7"}, delimiter = ':')
    @DisplayName("잘못된 pinCount로 spare생성시 예외 발생 테스트")
    void create_spare_with_invalid_pinCounts(String first, String second) {
        PinCount firstPinCount = new PinCount(first);
        PinCount secondPinCount = new PinCount(second);
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Spare(firstPinCount,secondPinCount));
    }
}
