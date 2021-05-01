package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateBonus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStateBonusTest {
    @Test
    void When_Double_Then_TwoStrikeSymbol() {
        FrameState state = new FrameStateBonus(new Pinfall(10));
        state.roll(new Pinfall(10));
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.STRIKE, PointSymbol.STRIKE));
    }

    @Test
    void When_Triple_Then_ThreeStrikeSymbol() {
        FrameState state = new FrameStateBonus(new Pinfall(10));
        state = state.roll(new Pinfall(10));
        state = state.roll(new Pinfall(10));
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE, PointSymbol.STRIKE)));
    }

    @Test
    void When_Spare_Then_NumbericAndSpareSymbol() {
        FrameState state = new FrameStateBonus(Arrays.asList(new Pinfall(1), new Pinfall(9)));

        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.ONE, PointSymbol.SPARE));
    }
}
