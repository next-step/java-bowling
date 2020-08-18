package bowling.domain.frame;

import bowling.domian.frame.NormalFrameResult;
import bowling.domian.state.Pins;
import bowling.domian.state.finished.Miss;
import bowling.domian.state.finished.Strike;
import bowling.domian.state.running.FirstBowl;
import bowling.domian.state.running.Ready;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameResultTest {
    @Test
    @DisplayName("점수 계산 가능 확인")
    public void canCalculate() {
        NormalFrameResult normalFrameResult = NormalFrameResult.get(new Strike());

        assertThat(normalFrameResult.canCalculateScore()).isTrue();
    }

    @Test
    @DisplayName("점수 계산 불가능 확인")
    public void canNotCalculate() {
        NormalFrameResult normalFrameResult = NormalFrameResult.get(new Ready());

        assertThat(normalFrameResult.canCalculateScore()).isFalse();
    }

    @Test
    @DisplayName("추가 계산이 완료되지 않은 경우 기존 Score 반납")
    public void additionalCalculationRemain() {
        NormalFrameResult lastNormalFrameResult = NormalFrameResult.get(new Strike());
        NormalFrameResult normalFrameResult = NormalFrameResult.get(new FirstBowl(Pins.bowl(3)));

        normalFrameResult.calculateAdditional(lastNormalFrameResult);

        assertThat(lastNormalFrameResult.isCalculateDone()).isFalse();
        assertThat(lastNormalFrameResult.getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("추가 계산 완료")
    public void additionalCalculation() {
        NormalFrameResult lastNormalFrameResult = NormalFrameResult.get(new Strike());
        NormalFrameResult normalFrameResult = NormalFrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(5)));

        normalFrameResult.calculateAdditional(lastNormalFrameResult);

        assertThat(lastNormalFrameResult.isCalculateDone()).isTrue();
        assertThat(lastNormalFrameResult.getScore()).isEqualTo(18);
    }


    @Test
    @DisplayName("이전 프레임까지 전체 점수 추가 계산")
    public void totalCalculationWithLastFrameResult() {
        NormalFrameResult normalFrameResult = NormalFrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(5)));

        assertThat(normalFrameResult.addTotalScore(10)).isEqualTo(18);
    }
}
