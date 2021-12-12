package bowling.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultsTest {

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(new FrameResults(Arrays.asList(new FrameResult("5"))))
                .isEqualTo(new FrameResults(Arrays.asList(new FrameResult("5"))));
    }
}
