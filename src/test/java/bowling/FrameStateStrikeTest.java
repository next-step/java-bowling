package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.Score;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateStrike;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FrameStateStrikeTest {
    @Test
    @DisplayName("공을 굴리면 Exception 발생 테스트")
    void When_Roll_Then_Exception() {
        FrameState strikeState = new FrameStateStrike();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> strikeState.roll(new Pinfall(9)))
                .withMessage("공을 굴릴 수 없습니다");
    }

    @Test
    @DisplayName("항상 굴릴 수 없음 테스트")
    void When_isRollable_Then_False() {
        FrameState strikeState = new FrameStateStrike();
        assertThat(strikeState.isRollable()).isFalse();
    }

    @Test
    @DisplayName("Strike Symbol(X) 반환 테스트")
    void When_Symbol_Then_WithSrikeSymbol() {
        FrameState state = new FrameStateStrike();
        AssertionsForInterfaceTypes.assertThat(state.pointSymbols().symbols()).contains(PointSymbol.STRIKE);
    }

    @Test
    @DisplayName("보너스 Pinfall이 없을 때 점수는 NotDetermined")
    void When_Score_Then_ScoreNotDetermined() {
        AssertionsForClassTypes.assertThat(new FrameStateStrike().score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("Strike의 넘어진 핀은 10개")
    void When_Pinfalls_Then_10() {
        assertThat(new FrameStateStrike().pinfalls()).isEqualTo(Arrays.asList(new Pinfall(10)));
    }
}
