package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameResultTest {
    @Test
    @DisplayName("보너스 점수 타입 확인")
    void isBonusResultTest() {
        assertThat(FrameResult.SPARE.isBonusResult()).isTrue();
        assertThat(FrameResult.STRIKE.isBonusResult()).isTrue();

        assertThat(FrameResult.NONE.isBonusResult()).isFalse();
        assertThat(FrameResult.MISS.isBonusResult()).isFalse();
    }
}
