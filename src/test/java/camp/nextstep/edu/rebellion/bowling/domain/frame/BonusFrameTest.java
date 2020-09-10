package camp.nextstep.edu.rebellion.bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BonusFrameTest {
    @DisplayName("보너스 프레임이 잘 생성 되는지 확인")
    @Test
    public void bonusFrameTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.BONUS, 1);

        // when & then
        assertAll(
                () -> assertThat(frame.isStarted()).isFalse(),
                () -> assertThat(frame.meetEnd()).isFalse()
        );
    }

    @DisplayName("보너스 프레임은 투구를 한 번만 할 수 있는지 확인")
    @Test
    public void markScoreTest() {
        // given
        int first = 1;
        int initAttemptCount = 1;
        Frame frame = FrameFactory.get(FrameType.BONUS, initAttemptCount);

        // when
        frame.markScore(first);

        // then
        assertAll(
                () -> assertThat(frame.isStarted()).isTrue(),
                () -> assertThat(frame.meetEnd()).isTrue(),
                () -> assertThat(frame.getFrameScore().getFirstScore()).isEqualTo(first)
        );
    }
}
