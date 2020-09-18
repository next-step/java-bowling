package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("핀갯수 객체 테스트")
public class NumberOfPinTest {

    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThat(new NumberOfPin(5)).isEqualTo(new NumberOfPin(5));
    }

    @DisplayName("핀갯수 유효성 테스트: 0보다 작거나 10보다 큰 수 입력")
    @ParameterizedTest
    @ValueSource(ints = { -5, 12 })
    public void validate_create(int numberOfPins) {
        assertThatIllegalArgumentException().isThrownBy(() -> new NumberOfPin(numberOfPins));
    }

}
