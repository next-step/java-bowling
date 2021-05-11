package bowling.domain.state;

import bowling.exception.IllegalPointException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StartTest {

    private Start start;

    @BeforeEach
    void init() {
        start = Start.createOf();
    }

    @DisplayName("볼링핀의 갯수 Validate 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1,11,20,-11,12})
    void exceptionTest(int number) {
        assertThrows(IllegalPointException.class, () -> start.next(number));
    }

    @DisplayName("next가 Ready일 경우 테스트")
    @Test
    void nextMissTest() {
        PitchState next = start.next(4);

        assertTrue(next instanceof Ready);
    }

    @DisplayName("next가 Strike일 경우 테스트")
    @Test
    void nextStrikeTest() {
        PitchState next = start.next(10);

        assertTrue(next instanceof Strike);
    }

}