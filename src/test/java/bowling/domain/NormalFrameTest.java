package bowling.domain;

import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("NormalFrame: 1~9 프레임 테스트")
public class NormalFrameTest {

    @DisplayName("1번 던져서 넘긴 핀이 10개면 New 반환")
    @Test
    public void bowling_Strike_ReturnFinish() {
        Frame frame = NormalFrame.first();
        assertThat(frame.bowling(new Pin(10))).isEqualTo(State.ofNew());
    }

    @DisplayName("1번 던져서 넘긴 핀이 10개 미만 NotFinish 반환")
    @Test
    public void bowling_Miss_ReturnNotFinish() {
        Frame frame = NormalFrame.first();
        assertThat(frame.bowling(new Pin(5))).isEqualTo(State.ofSpare(5));
    }

    @DisplayName("2번 던지서 Miss면 New 반환")
    @Test
    public void bowling_TwoTime_ReturnFinish() {
        Frame frame = NormalFrame.first();
        frame.bowling(new Pin(3));
        assertThat(frame.bowling(new Pin(3))).isEqualTo(State.ofNew());
    }

    @DisplayName("라운드는 1부터 시작")
    @Test
    public void 라운드는_1부터_시작() {
        Frames frames = new Frames(new Player("jae"));
        assertThat(frames.getCurrentFrameNo()).isEqualTo(1);
    }

    @DisplayName("프레임이 종료되면 프레임이 증가한다")
    @Test
    public void 프레임이_종료되면_프레임이_증가한다() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(10));
        assertThat(frames.getCurrentFrameNo()).isEqualTo(2);
    }

    @DisplayName("프레임이 종료되지않으면 프레임이 유지된다")
    @Test
    public void 프레임이_종료되지않으면_프레임이_유지된다() {
        Frames frames = new Frames(new Player("jae"));
        frames.bowling(new Pin(5));
        assertThat(frames.getCurrentFrameNo()).isEqualTo(1);
    }
}
