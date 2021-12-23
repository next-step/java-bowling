package bowling.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {

    @Test
    @DisplayName("생성 테스트")
    void createTest() {
        Results results = new Results();

        assertThat(results.getGameResults().size()).isEqualTo(10);
    }
}
