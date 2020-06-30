package bowling.tobe;

import bowling.domain.Pin;
import bowling.domain.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("NormalFrame: 1~9 프레임 테스트")
public class NormalFrameTest {

    @DisplayName("1번 던져서 넘긴 핀이 10개면 Finish 반환")
    @Test
    public void bowling_Strike_ReturnFinish() {
        Frame frame = NormalFrame.first();
        assertThat(frame.bowling(new Pin(10))).isEqualTo(State.Finish);
    }

    @DisplayName("1번 던져서 넘긴 핀이 10개 미만 NotFinish 반환")
    @Test
    public void bowling_Miss_ReturnNotFinish() {
        Frame frame = NormalFrame.first();
        assertThat(frame.bowling(new Pin(5))).isEqualTo(State.NotFinish);
    }

    @DisplayName("2번 던지면 Finish 반환")
    @Test
    public void bowling_TwoTime_ReturnFinish() {
        Frame frame = NormalFrame.first();
        frame.bowling(new Pin(3));
        assertThat(frame.bowling(new Pin(3))).isEqualTo(State.Finish);
    }
}
