package bowling.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultTest {

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(new FrameResult("5")).isEqualTo(new FrameResult("5"));
    }

}
