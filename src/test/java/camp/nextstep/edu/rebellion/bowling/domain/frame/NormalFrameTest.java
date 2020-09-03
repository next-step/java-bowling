package camp.nextstep.edu.rebellion.bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {
    @DisplayName("일반 프레임 생성이 잘 되는지 확인")
    @Test
    public void normalFrameTest() {
        // given
        Frame frame = new NormalFrame();

        // when & then
        assertAll(
                () -> assertThat(frame.isStarted()).isFalse(),
                () -> assertThat(frame.meetEnd()).isFalse()
        );
    }

    @DisplayName("프레임의 상태 값이 및 점수가 잘 기록되는지 확인")
    @Test
    public void markScoreTest() {
        // given
        int first = 1;
        int last = 2;
        Frame frame = new NormalFrame();

        // when
        frame.markScore(first);

        // then
        assertAll(
                () -> assertThat(frame.isStarted()).isTrue(),
                () -> assertThat(frame.meetEnd()).isFalse(),
                () -> assertThat(frame.getFrameScore().getFirstScore()).isEqualTo(first)
        );

        // and
        frame.markScore(last);

        // then
        assertAll(
                () -> assertThat(frame.isStarted()).isTrue(),
                () -> assertThat(frame.meetEnd()).isTrue(),
                () -> assertThat(frame.getFrameScore().getFirstScore()).isEqualTo(first),
                () -> assertThat(frame.getFrameScore().getLastScore()).isEqualTo(last)
        );
    }
}
