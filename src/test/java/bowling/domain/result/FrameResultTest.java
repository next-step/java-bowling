package bowling.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultTest {

    public static FrameResult STRIKE_RESULT = new FrameResult("X");
    public static FrameResult FIVE_POINT_RESULT = new FrameResult("5");

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(STRIKE_RESULT).isEqualTo(new FrameResult("X"));
    }

}
