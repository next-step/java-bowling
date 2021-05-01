package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateBonus;
import bowling.domain.state.FrameStateFinalOnce;
import bowling.domain.state.FrameStateOpen;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameStateFinalOnceTest {
    @Test
    void When_New_Then_Create() {
        assertDoesNotThrow(() -> new FrameStateFinalOnce(new Pinfall(1)));
    }

    @Test
    void When_Spare_Then_BonusState() {
        FrameState state = new FrameStateFinalOnce(new Pinfall(1));
        assertThat(state.roll(new Pinfall(9))).isInstanceOf(FrameStateBonus.class);
    }

    @Test
    void When_Open_Then_OpenState() {
        FrameState state = new FrameStateFinalOnce(new Pinfall(1));
        assertThat(state.roll(new Pinfall(2))).isInstanceOf(FrameStateOpen.class);
    }

    @Test
    void When_isRollable_Then_True() {
        assertThat(new FrameStateFinalOnce(new Pinfall(1)).isRollable()).isTrue();
    }

    @Test
    void When_PointSymbol_Then_RightSymbols() {
        FrameState state = new FrameStateFinalOnce(new Pinfall(1));
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.ONE));
    }
}
