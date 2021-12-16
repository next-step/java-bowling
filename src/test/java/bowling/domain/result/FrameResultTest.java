package bowling.domain.result;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;
import bowling.domain.state.FirstBowl;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {

    public static FrameResult STRIKE_RESULT = FrameResult.ofNormalFrame(new Strike(), Score.of(-1, 0));
    public static FrameResult FIVE_POINT_RESULT = FrameResult.ofNormalFrame(new FirstBowl(Pin.from(5)), Score.of(-1, 0));
    public static FrameResult READY = FrameResult.ofNormalFrame(Ready.getInstance(), Score.of(-1, 0));

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(STRIKE_RESULT).isEqualTo(FrameResult.ofNormalFrame(new Strike(), Score.of(-1, 0)));
    }

}
