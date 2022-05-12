package bowling.domain.pin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinNumbersTest {

    @ParameterizedTest(name = "첫 번째 투구가 스트라이크가 아니면 두 투구의 합이 10을 넘을 수 없다")
    @CsvSource({"8,4", "7, 5", "1, 10"})
    void validation(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new PinNumbers(firstNo, secondNo))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void equals() {
        assertThat(new PinNumbers(5, 3)).isEqualTo(new PinNumbers(5, 3));
    }
}
