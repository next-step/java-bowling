package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.exception.ScoreListNullPointerException;
import bowling.exception.ScoreNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("FrameResult 인스턴스 생성 여부 테스트")
    @Test
    void 검증() {
        // given
        Frame frame = FinalFrame.initialize();
        Score score = null;

        // when
        assertThatThrownBy(() -> FrameResult.of(frame, score))
                .isInstanceOf(ScoreNullPointerException.class)
                .hasMessage("Score 인스턴스가 null 입니다.");

    }
}