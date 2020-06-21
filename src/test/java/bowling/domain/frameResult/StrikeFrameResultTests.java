package bowling.domain.frameResult;

import bowling.domain.FrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeFrameResultTests {
    @DisplayName("언제나 온전한 stike FrameResult임을 보장한다.")
    @Test
    void createTest() {
        StrikeFrameResult strikeFrameResult = new StrikeFrameResult();

        assertThat(strikeFrameResult.isStrikeResult()).isTrue();
        assertThat(strikeFrameResult.isCompleted()).isTrue();
    }

    @DisplayName("현재 상태를 알려줄 수 있다.")
    @Test
    void getStatusTest() {
        StrikeFrameResult strikeFrameResult = new StrikeFrameResult();

        assertThat(strikeFrameResult.calculateCurrentStatus()).isEqualTo(FrameStatus.STRIKE);
    }
}
