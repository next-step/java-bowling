package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwingRecordTest {

    @DisplayName("toString 변환 테스트")
    @CsvSource(value = {"0,-", "1,1", "10,X"})
    @ParameterizedTest
    void toStringTest(int score, String scoreStr) {
        SwingRecord swingRecord = new SwingRecord();
        swingRecord.addHistory(score);

        assertEquals(swingRecord.toString(), scoreStr);
    }

    @DisplayName("toString 변환 테스트")
    @Test
    void multiHistoryToStringTest() {
        SwingRecord swingRecord = new SwingRecord();
        swingRecord.addHistory(0);
        swingRecord.addHistory(10);

        String result = swingRecord.toString();

        assertEquals(result.split("\\|").length, 2);
        assertEquals(swingRecord.toString(), "-|X");
    }

    @DisplayName("toString 변환 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 10})
    @ParameterizedTest
    void firstSwingTest(int score) {
        SwingRecord swingRecord = new SwingRecord();
        swingRecord.addHistory(score);
        assertEquals(swingRecord.firstSwing(), score);
    }
}
