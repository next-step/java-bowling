package bowling.entity;

import bowling.entity.exception.ScoreOutOfRangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void test_plus() {
        // Given
        Score first = Score.of(3);
        Score second = Score.of(5);

        // When
        Score plus = first.plus(second);

        // Then
        assertEquals(Score.of(8), plus);
    }

    @Test
    void test_out_of_rage() {
        assertThrows(ScoreOutOfRangeException.class, () -> Score.of(-1));
    }

}