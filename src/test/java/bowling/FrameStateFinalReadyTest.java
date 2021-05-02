package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import bowling.domain.state.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameStateFinalReadyTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Create() {
        assertDoesNotThrow(bowling.domain.state.FrameStateFinalReady::new);
    }

    @Test
    @DisplayName("Stike를 치면 BonusState로 Transition")
    void Given_Strike_When_Roll_Then_FrameStateBonus() {
        FrameState state = new FrameStateFinalReady();
        assertThat(state.roll(new Pinfall(10))).isInstanceOf(FrameStateBonus.class);
    }

    @Test
    @DisplayName("항상 굴릴 수 있음 테스트")
    void When_isRollable_Then_True() {
        assertThat(new FrameStateFinalReady().isRollable()).isTrue();
    }

    @Test
    @DisplayName("Strike가 아니면 StateFinalOnce로 Transition")
    void When_Roll_Then_FrameState_FrameStateFinalOnce() {
        FrameState state = new FrameStateFinalReady();
        assertThat(state.roll(new Pinfall(1))).isInstanceOf(FrameStateFinalOnce.class);
    }

    @Test
    @DisplayName("빈 PointSymbol 테스트")
    void When_PointSymbols_Then_EmptyPointSymbols() {
        FrameState state = new FrameStateFinalReady();
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols());
    }

    @Test
    @DisplayName("점수는 항상 NotDetermined 테스트")
    void When_Score_Then_ScoreNotDetermined() {
        AssertionsForClassTypes.assertThat(new FrameStateFinalReady().score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("넘어진 핀은 없음 테스트")
    void When_Pinfalls_Then_Empty() {
        AssertionsForInterfaceTypes.assertThat(new FrameStateFinalReady().pinfalls()).isEqualTo(new ArrayList<>());
    }
}
