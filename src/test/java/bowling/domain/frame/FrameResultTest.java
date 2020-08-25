package bowling.domain.frame;

import bowling.domian.frame.FrameResult;
import bowling.domian.frame.Score;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @Test
    @DisplayName("점수 계산이 끝나지 않은 경우 전체 점수 계산 불가")
    public void cannotCalculateTotalScore(){
        FrameResult frameResult = FrameResult.of("");

        assertThat(frameResult.canAddTotal()).isFalse();
    }

    @Test
    @DisplayName("점수 계산이 끝난 경우 전체 점수 계산")
    public void calculateTotalScore(){
        int lastTotalScore = 10;
        int falledPinsCount = 4;

        FrameResult frameResult =
                FrameResult.of(Score.miss(falledPinsCount), "");

        assertThat(frameResult.canAddTotal()).isTrue();
        assertThat(frameResult.addTotalScore(lastTotalScore)).isEqualTo(lastTotalScore + falledPinsCount);
    }
}
