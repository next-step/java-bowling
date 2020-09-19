package bowling;

import bowling.domain.FinalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @DisplayName("완전 끝나지 않으면 증가 안함")
    @Test
    public void finalFrameTestFirstHit() {
        FinalFrame finalFrame = FinalFrame.init();
        int result = 5;
        result = finalFrame.hit(5, result);
        assertThat(result).isEqualTo(5);
    }

    @DisplayName("스페어 처리 못할 시 증가")
    @Test
    public void finalFrameTestSecondHitWithoutSpare() {
        FinalFrame finalFrame = FinalFrame.init();
        int result = 5;
        result = finalFrame.hit(5, result);
        result = finalFrame.hit(3, result);
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("스페어 처리완료 시 미증가")
    @Test
    public void finalFrameTestLastIsSpare2() {
        FinalFrame finalFrame = FinalFrame.init();
        int result = 5;
        result = finalFrame.hit(5, result);
        result = finalFrame.hit(5, result);
        assertThat(result).isEqualTo(5);
    }

    @DisplayName("스페어 처리 이후 한번 더 치고 증가")
    @Test
    public void finalFrameTestLastIsSpareAndHit() {
        FinalFrame finalFrame = FinalFrame.init();
        int result = 5;
        result = finalFrame.hit(5, result);
        result = finalFrame.hit(5, result);
        result = finalFrame.hit(10, result);
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("처음 스트라이크 시 미증가")
    @Test
    public void finalFrameFirstIsStrike() {
        FinalFrame finalFrame = FinalFrame.init();
        int result = 5;
        result = finalFrame.hit(10, result);
        assertThat(result).isEqualTo(5);
    }

    @DisplayName("처음 스트라이크 시 한번 더 치고")
    @Test
    public void finalFrameFirstIsStrikeAndHit() {
        FinalFrame finalFrame = FinalFrame.init();
        int result = 5;
        result = finalFrame.hit(10, result);
        result = finalFrame.hit(10, result);
        assertThat(result).isEqualTo(6);
    }



}
