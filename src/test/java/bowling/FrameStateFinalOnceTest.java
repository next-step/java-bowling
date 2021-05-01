package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import bowling.domain.state.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameStateFinalOnceTest {
    Pinfall pinfall = new Pinfall(1);

    @Test
    void When_New_Then_Create() {
        assertDoesNotThrow(() -> new FrameStateFinalOnce(pinfall));
    }

    @Test
    void When_Spare_Then_BonusState() {
        FrameState state = new FrameStateFinalOnce(pinfall);
        assertThat(state.roll(new Pinfall(9))).isInstanceOf(FrameStateBonus.class);
    }

    @Test
    void When_Open_Then_OpenState() {
        FrameState state = new FrameStateFinalOnce(pinfall);
        assertThat(state.roll(new Pinfall(2))).isInstanceOf(FrameStateOpen.class);
    }

    @Test
    void When_isRollable_Then_True() {
        assertThat(new FrameStateFinalOnce(pinfall).isRollable()).isTrue();
    }

    @Test
    void When_PointSymbol_Then_RightSymbols() {
        FrameState state = new FrameStateFinalOnce(pinfall);
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.ONE));
    }

    @Test
    void When_Score_Then_ScoreNotDetermined() {
        AssertionsForClassTypes.assertThat(new FrameStateFinalOnce(pinfall).score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    void When_Pinfalls_Then_Pinfall() {
        AssertionsForInterfaceTypes.assertThat(new FrameStateFinalOnce(pinfall).pinfalls()).isEqualTo(Arrays.asList(pinfall));
    }
}
