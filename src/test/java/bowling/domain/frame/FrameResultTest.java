package bowling.domain.frame;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameResultTest {

    @DisplayName("FrameResult 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Frame frame = FinalFrame.initialize();
        Score score = frame.score();

        // when
        FrameResult frameResult = FrameResult.of(frame, score);

        // then
        assertThat(frameResult).isNotNull();
    }
}