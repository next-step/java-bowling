package bowling.domain;

import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FinalFrame: 10 프레임 테스트")
public class FinalFrameTest {

    @DisplayName("1번 던져서 넘긴 핀이 10개면 NotFinish 반환")
    @Test
    public void bowling_OneTimeStrike_ReturnNotFinish() {
        Frame frame = FinalFrame.last();
        assertThat(frame.bowling(new Pin(10))).isEqualTo(State.ofSpare(10));
    }

    @DisplayName("1번 던져서 넘긴 핀이 10개 미만 NotFinish 반환")
    @Test
    public void bowling_OneTimeMiss_ReturnNotFinish() {
        Frame frame = FinalFrame.last();
        assertThat(frame.bowling(new Pin(5))).isEqualTo(State.ofSpare(5));
    }

    @DisplayName("2번 던져서 넘긴 핀이 10개면 NotFinish 반환")
    @Test
    public void bowling_TwoTimeSpare_ReturnBonus() {
        Frame frame = FinalFrame.last();
        frame.bowling(new Pin(5));
        assertThat(frame.bowling(new Pin(5))).isEqualTo(State.ofSpare(10));
    }

    @DisplayName("2번 던져서 넘긴 핀이 10개 미만 Finish 반환")
    @Test
    public void bowling_TwoTimeMiss_ReturnFinish() {
        Frame frame = FinalFrame.last();
        frame.bowling(new Pin(5));
        assertThat(frame.bowling(new Pin(3))).isEqualTo(State.ofFinish());
    }

    @DisplayName("1번 던져서 넘긴 핀이 10개이면, 보너스 투구 후에 Finish 반환")
    @Test
    public void bowling_AfterStrike_ReturnFinish() {
        Frame frame = FinalFrame.last();
        frame.bowling(new Pin(10));
        assertThat(frame.bowling(new Pin(5))).isEqualTo(State.ofFinish());
    }

    @DisplayName("2번 던져서 넘긴 핀이 10개이면, 보너스 투구 후에 Finish 반환")
    @Test
    public void bowling_AfterSpare_ReturnFinish() {
        Frame frame = FinalFrame.last();
        frame.bowling(new Pin(5));
        frame.bowling(new Pin(5));
        assertThat(frame.bowling(new Pin(5))).isEqualTo(State.ofFinish());
    }
}
