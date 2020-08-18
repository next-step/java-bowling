package bowling.domain.frame;

import bowling.domian.frame.FrameResult;
import bowling.domian.state.Pins;
import bowling.domian.state.finished.Miss;
import bowling.domian.state.finished.Strike;
import bowling.domian.state.running.FirstBowl;
import bowling.domian.state.running.Ready;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @Test
    @DisplayName("점수 계산 가능 확인")
    public void canCalculate() {
        FrameResult frameResult = FrameResult.get(new Strike(), 0);

        assertThat(frameResult.canCalculateScore()).isTrue();
    }

    @Test
    @DisplayName("점수 계산 불가능 확인")
    public void canNotCalculate() {
        FrameResult frameResult = FrameResult.get(new Ready(), 0);

        assertThat(frameResult.canCalculateScore()).isFalse();
    }

    @Test
    @DisplayName("추가 계산이 완료되지 않은 경우 기존 Score 반납")
    public void additionalCalculationRemain() {
        FrameResult lastFrameResult = FrameResult.get(new Strike(), 0);
        FrameResult frameResult = FrameResult.get(new FirstBowl(Pins.bowl(3)));

        frameResult.calculateAdditional(lastFrameResult);

        assertThat(lastFrameResult.isCalculateDone()).isFalse();
        assertThat(lastFrameResult.getTotalScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("추가 계산 완료")
    public void additionalCalculation() {
        FrameResult lastFrameResult = FrameResult.get(new Strike(), 0);
        FrameResult frameResult = FrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(5)));

        frameResult.calculateAdditional(lastFrameResult);

        assertThat(lastFrameResult.isCalculateDone()).isTrue();
        assertThat(lastFrameResult.getTotalScore()).isEqualTo(18);
    }

    @Test
    @DisplayName("이전 프레임까지 전체 점수 추가 계산")
    public void totalCalculationWithLastFrameResult() {
        FrameResult lastFrameResult = FrameResult.get(new Strike(), 0);
        FrameResult frameResult = FrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(5)));

        frameResult.calculateAdditional(lastFrameResult);
        frameResult.addLastTotalScore(lastFrameResult);

        assertThat(lastFrameResult.isCalculateDone()).isTrue();
        assertThat(lastFrameResult.getTotalScore()).isEqualTo(18);
    }
}
