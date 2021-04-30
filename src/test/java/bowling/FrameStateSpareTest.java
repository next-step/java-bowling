package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateSpare;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class FrameStateSpareTest {

    @Test
    void When_Roll_Then_Exception() {
        FrameState state = new FrameStateSpare();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> state.roll(new Pinfall(9)))
                .withMessage("공을 굴릴 수 없습니다");
    }

    @Test
    void When_isRollable_Then_False() {
        FrameState state = new FrameStateSpare();
        assertThat(state.isRollable()).isFalse();
    }

    @Test
    void When_Symbol_Then_WithSpareSymbol() {
        FrameState state = new FrameStateSpare(new Pinfall(9));
        AssertionsForInterfaceTypes.assertThat(state.pointSymbols().symbols()).containsAll(Arrays.asList(PointSymbol.NINE, PointSymbol.SPARE));
    }
}
