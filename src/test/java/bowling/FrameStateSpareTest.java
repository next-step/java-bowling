package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.Score;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateSpare;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class FrameStateSpareTest {
    private List<Pinfall> sparePinfalls = Arrays.asList(Pinfall.create(9), Pinfall.create(1));

    @Test
    @DisplayName("공을 굴리면 Exception 발생 테스트")
    void When_Roll_Then_Exception() {
        FrameState state = new FrameStateSpare();
        assertThatExceptionOfType(IllegalRollingSequenceException.class)
                .isThrownBy(() -> state.roll(Pinfall.create(9)))
                .withMessage("공을 굴릴 수 없습니다");
    }

    @Test
    @DisplayName("항상 굴릴 수 없음 테스트")
    void When_isRollable_Then_False() {
        FrameState state = new FrameStateSpare();
        assertThat(state.isRollable()).isFalse();
    }

    @Test
    @DisplayName("Pinfall에 해당하는 PointSymbol 반환 테스트")
    void When_Symbol_Then_WithSpareSymbol() {
        FrameState state = new FrameStateSpare(sparePinfalls);
        AssertionsForInterfaceTypes.assertThat(state.pointSymbols().symbols()).containsAll(Arrays.asList(PointSymbol.NINE, PointSymbol.SPARE));
    }

    @Test
    @DisplayName("보너스 Pinfall이 없을 때 점수는 NotDetermined")
    void When_Score_Then_ScoreNotDetermined() {
        assertThat(new FrameStateSpare(sparePinfalls).score()).isEqualTo(Score.createNotDetermined());
    }

    @Test
    @DisplayName("입력한 Pinfall을 잘 반환하는지 테스트")
    void When_pinfalls_Then_Pinfalls() {
        AssertionsForInterfaceTypes.assertThat(new FrameStateSpare(sparePinfalls).pinfalls()).isEqualTo(sparePinfalls);
    }
}
