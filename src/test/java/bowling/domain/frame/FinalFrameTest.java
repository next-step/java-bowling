package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.score.FinalScore;
import bowling.exception.frame.FinalFrameCreateFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    @DisplayName("final frame은 다음 frame을 생성할 수 없다.")
    void failCreateNextFrameTest() {

        // given
        Frame input = FinalFrame.of(FinalScore.emptyScore());

        // when & then
        assertThatExceptionOfType(FinalFrameCreateFrameException.class)
            .isThrownBy(() -> input.createNextFrame())
            .withMessageMatching("final frame은 다음 frame을 생성할 수 없습니다.");
    }
}