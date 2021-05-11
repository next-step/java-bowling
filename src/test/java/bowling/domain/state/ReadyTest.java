package bowling.domain.state;

import bowling.exception.IllegalPointException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @DisplayName("볼링핀의 갯수 Validate 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1,11,20,-11,12})
    void exceptionTest(int number) {
        assertThrows(IllegalPointException.class, () -> new Ready(number));
    }

    @DisplayName("next가 Miss일 경우 테스트")
    @Test
    void nextMissTest() {
        Ready ready = new Ready(5);
        PitchState next = ready.next(4);

        assertTrue(next instanceof Miss);
    }

    @DisplayName("next가 Spare일 경우 테스트")
    @Test
    void nextSpareTest() {
        Ready ready = new Ready(1);
        PitchState next = ready.next(9);

        assertTrue(next instanceof Spare);
    }
}