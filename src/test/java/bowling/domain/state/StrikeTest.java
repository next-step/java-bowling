package bowling.domain.state;

import bowling.exception.IllegalPointException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    @DisplayName("볼링핀의 갯수 Validate 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1,11,20,-11,12,6,7,4})
    void exceptionTest(int number) {
        assertThrows(IllegalPointException.class, () -> new Strike(number));
    }
}