package bowling.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ResultsTest {

    @Test
    @DisplayName("생성 테스트")
    void createTest() {
        assertDoesNotThrow(() -> new Results());

    }
}
