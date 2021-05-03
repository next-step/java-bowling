package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class FrameStateOnceTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Created() {
        assertThat(new FrameStateOnce(Pinfall.create(1))).isEqualTo(new FrameStateOnce(Pinfall.create(1)));
    }

    @Test
    @DisplayName("Spare이면 StateSpare로 Transition")
    void Given_Spare_When_Roll_Then_StateSpare() {
        FrameState state = new FrameStateOnce(Pinfall.create(1));
        assertThat(state.roll(Pinfall.create(9))).isInstanceOf(FrameStateSpare.class);
    }

    @Test
    @DisplayName("스페어 처리가 되었을 때, 스페어 상태의 PointSymbol이 정상인지 테스트")
    void Given_Spare_When_Roll_Then_StateSpareFirstPointSymbolIsSameAsFirstSymbol() {
        FrameState stateOnce = new FrameStateOnce(Pinfall.create(1));
        FrameState stateSpare = stateOnce.roll(Pinfall.create(9));

        assertThat(stateSpare.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.ONE, PointSymbol.SPARE));

    }

    @Test
    @DisplayName("Open이면 StateOpen으로 Transition")
    void Given_Open_When_Roll_Then_StateOpen() {
        FrameState state = new FrameStateOnce(Pinfall.create(1));
        assertThat(state.roll(Pinfall.create(2))).isInstanceOf(FrameStateOpen.class);
    }

    @Test
    @DisplayName("항상 굴릴 수 있음 테스트")
    void When_isRollable_Then_True() {
        FrameState state = new FrameStateOnce(Pinfall.create(1));
        assertThat(state.isRollable()).isTrue();
    }

    @Test
    @DisplayName("Pinfall에 해당하는 PointSymbol 반환 테스트")
    void When_Symbol_Then_RightSymbol() {
        FrameState state = new FrameStateOnce(Pinfall.create(1));
        assertThat(state.pointSymbols().symbols()).contains(PointSymbol.ONE);
    }

    @Test
    @DisplayName("점수는 항상 NotDetermined 테스트")
    void When_Score_Then_NotDetermined() {
        assertThat(new FrameStateOnce(Pinfall.create(1)).score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("입력한 Pinfall을 잘 반환하는지 테스트")
    void When_Pinfalls_Then_FirstPinfall() {
        assertThat(new FrameStateOnce(Pinfall.create(1)).pinfalls()).isEqualTo(Arrays.asList(Pinfall.create(1)));
    }
}
