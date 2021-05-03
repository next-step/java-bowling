package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import bowling.domain.state.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameStateFinalOnceTest {
    Pinfall pinfall = Pinfall.create(1);

    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Create() {
        assertDoesNotThrow(() -> new FrameStateFinalOnce(pinfall));
    }

    @Test
    @DisplayName("스페어가 되면 BonusState로 Transition")
    void When_Spare_Then_BonusState() {
        FrameState state = new FrameStateFinalOnce(pinfall);
        assertThat(state.roll(Pinfall.create(9))).isInstanceOf(FrameStateBonus.class);
    }

    @Test
    @DisplayName("Open이면 OpenState로 Transition")
    void When_Open_Then_OpenState() {
        FrameState state = new FrameStateFinalOnce(pinfall);
        assertThat(state.roll(Pinfall.create(2))).isInstanceOf(FrameStateOpen.class);
    }

    @Test
    @DisplayName("항상 굴릴 수 있음 테스트")
    void When_isRollable_Then_True() {
        assertThat(new FrameStateFinalOnce(pinfall).isRollable()).isTrue();
    }

    @Test
    @DisplayName("Pinfall에 해당하는 PointSymbol 반환 테스트")
    void When_PointSymbol_Then_RightSymbols() {
        FrameState state = new FrameStateFinalOnce(pinfall);
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.ONE));
    }

    @Test
    @DisplayName("점수는 항상 NotDetermined 테스트")
    void When_Score_Then_ScoreNotDetermined() {
        AssertionsForClassTypes.assertThat(new FrameStateFinalOnce(pinfall).score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("입력한 Pinfall을 잘 반환하는지 테스트")
    void When_Pinfalls_Then_Pinfall() {
        AssertionsForInterfaceTypes.assertThat(new FrameStateFinalOnce(pinfall).pinfalls()).isEqualTo(Arrays.asList(pinfall));
    }
}
