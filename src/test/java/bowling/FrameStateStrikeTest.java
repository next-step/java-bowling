package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.Score;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateStrike;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FrameStateStrikeTest {
    @Test
    void When_Roll_Then_Exception() {
        FrameState strikeState = new FrameStateStrike();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> strikeState.roll(new Pinfall(9)))
                .withMessage("공을 굴릴 수 없습니다");
    }

    @Test
    void When_isRollable_Then_False() {
        FrameState strikeState = new FrameStateStrike();
        assertThat(strikeState.isRollable()).isFalse();
    }

    @Test
    void When_Symbol_Then_WithSpareSymbol() {
        FrameState state = new FrameStateStrike();
        AssertionsForInterfaceTypes.assertThat(state.pointSymbols().symbols()).contains(PointSymbol.STRIKE);
    }

    @Test
    void When_Score_Then_Score10() {
        AssertionsForClassTypes.assertThat(new FrameStateStrike().score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    void When_Pinfalls_Then_10() {
        assertThat(new FrameStateStrike().pinfalls()).isEqualTo(Arrays.asList(new Pinfall(10)));
    }
}
