package bowling;

import bowling.domain.Pinfall;
import bowling.domain.Score;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateOnce;
import bowling.domain.state.FrameStateReady;
import bowling.domain.state.FrameStateStrike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameStateReadyTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Created() {
        assertDoesNotThrow(FrameStateReady::new);
    }

    @Test
    @DisplayName("Strike가 아니면 StateFinalOnce로 Transition")
    void When_Roll_Then_OnceState() {
        FrameState state = new FrameStateReady();
        assertThat(state.roll(new Pinfall(1))).isInstanceOf(FrameStateOnce.class);
    }

    @Test
    @DisplayName("Strike이면 StateStrike로 Transition")
    void Given_Strike_When_Roll_Then_FrameStateStrike() {
        FrameState stateReady = new FrameStateReady();
        assertThat(stateReady.roll(new Pinfall(10))).isInstanceOf(FrameStateStrike.class);
    }

    @Test
    @DisplayName("항상 굴릴 수 있음 테스트")
    void When_isRollable_Then_True() {
        FrameState stateReady = new FrameStateReady();
        assertThat(stateReady.isRollable()).isTrue();
    }

    @Test
    @DisplayName("점수는 항상 NotDetermined 테스트")
    void When_Score_Then_NotDetermined() {
        assertThat(new FrameStateReady().score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("넘어진 핀은 없음 테스트")
    void When_pinfalls_Then_Empty() {
        assertThat(new FrameStateReady().pinfalls()).isEqualTo(new ArrayList<>());
    }
}
