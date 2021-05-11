package bowling.domain.state;

import bowling.exception.IllegalPointException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("볼링핀의 갯수 Validate 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5:5", "10:0", "11:1", "4:6"}, delimiter = ':')
    void exceptionTest(int first, int second) {
        assertThrows(IllegalPointException.class, () -> new Miss(first, second));
    }
}