package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwingHistoryTest {

    @DisplayName("toString 변환 테스트")
    @CsvSource(value = {"0,-", "1,1", "10,X"})
    @ParameterizedTest
    void toStringTest(int score, String scoreStr) {
        SwingHistory swingHistory = new SwingHistory();
        swingHistory.addHistory(score);

        assertEquals(swingHistory.toString(), scoreStr);
    }

    @DisplayName("toString 변환 테스트")
    @Test
    void multiHistoryToStringTest() {
        SwingHistory swingHistory = new SwingHistory();
        swingHistory.addHistory(0);
        swingHistory.addHistory(10);

        String result = swingHistory.toString();

        assertEquals(result.split("\\|").length, 2);
        assertEquals(swingHistory.toString(), "-|X");
    }

    @DisplayName("toString 변환 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 10})
    @ParameterizedTest
    void firstSwingTest(int score) {
        SwingHistory swingHistory = new SwingHistory();
        swingHistory.addHistory(score);
        assertEquals(swingHistory.firstSwing(), score);
    }
}
