package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbols;
import bowling.domain.state.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FrameStateFinalReadyTest {
    @Test
    void When_New_Then_Create() {
        assertDoesNotThrow(bowling.domain.state.FrameStateFinalReady::new);
    }

    @Test
    void Given_Strike_When_Roll_Then_FrameStateBonus() {
        FrameState state = new FrameStateFinalReady();
        assertThat(state.roll(new Pinfall(10))).isInstanceOf(FrameStateBonus.class);
    }

    @Test
    void When_isRollable_Then_True() {
        assertThat(new FrameStateFinalReady().isRollable()).isTrue();
    }

    @Test
    void When_Roll_Then_FrameState_FrameStateFinalOnce() {
        FrameState state = new FrameStateFinalReady();
        assertThat(state.roll(new Pinfall(1))).isInstanceOf(FrameStateFinalOnce.class);
    }

    @Test
    void When_PointSYmbols_Then_EmptyPointSymbols() {
        FrameState state = new FrameStateFinalReady();
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols());
    }
}
