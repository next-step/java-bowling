package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @DisplayName("핀의 개수는 0~10 사이여야 한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 10})
    public void validate_success(int count) throws Exception {
        new Pins(count);
    }

    @DisplayName("핀의 개수가 0~10 사이가 아니면 exception")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 15})
    public void validate_fail(int count) throws Exception {
        assertThatThrownBy(
                () -> new Pins(count)
        ).isInstanceOf(BowlingException.class);
    }
}
