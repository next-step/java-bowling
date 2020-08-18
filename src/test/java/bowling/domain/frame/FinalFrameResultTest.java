package bowling.domain.frame;

import bowling.domian.frame.FinalFrameResult;
import bowling.domian.frame.NormalFrameResult;
import bowling.domian.state.Pins;
import bowling.domian.state.finished.Miss;
import bowling.domian.state.finished.Strike;
import bowling.domian.state.running.FirstBowl;
import bowling.domian.state.running.Ready;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameResultTest {
    @Test
    @DisplayName("점수 계산 가능 확인")
    public void canCalculate() {
        FinalFrameResult frameResult = FinalFrameResult.get(new Strike(), new FirstBowl(Pins.bowl(3)));

        assertThat(frameResult.canCalculateScore()).isTrue();
        assertThat(frameResult.getScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("추가 점수 계산")
    public void additionalCalculation() {
        NormalFrameResult lastNormalFrameResult = NormalFrameResult.get(new Strike());
        FinalFrameResult frameResult =
                FinalFrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(4)));

        frameResult.calculateAdditional(lastNormalFrameResult);

        assertThat(lastNormalFrameResult.isCalculateDone()).isTrue();
        assertThat(lastNormalFrameResult.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("투구를 시작하기 전 점수 계산 불가능 확인")
    public void canNotCalculateBeforeBowl() {
        FinalFrameResult frameResult = FinalFrameResult.get(new Ready());

        assertThat(frameResult.canCalculateScore()).isFalse();
    }

    @Test
    @DisplayName("보너스 투구가 필요한 경우 점수 계산 불가능 확인")
    public void canNotCalculateWhenFirstBowlStrike() {
        FinalFrameResult frameResult = FinalFrameResult.get(new Strike());

        assertThat(frameResult.canCalculateScore()).isFalse();
    }

    @Test
    @DisplayName("보너스 투구 필요하지 않은 경우 점수 계산")
    public void calculateScoreWithoutBonus() {
        FinalFrameResult frameResult =
                FinalFrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(4)));

        assertThat(frameResult.isCalculateDone()).isTrue();
        assertThat(frameResult.getScore()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 투구가 존재할 때 점수 계산")
    public void calculateScoreWithBonus() {
        FinalFrameResult frameResult =
                FinalFrameResult.get(new Strike(), new FirstBowl(Pins.bowl(3)));

        assertThat(frameResult.isCalculateDone()).isTrue();
        assertThat(frameResult.getScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("이전 프레임까지 전체 점수 추가 계산")
    public void totalCalculationWithLastFrameResult() {
        FinalFrameResult frameResult = FinalFrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(5)));

        frameResult.addTotalScore(10);

        assertThat(frameResult.getScore()).isEqualTo(18);
    }
}
