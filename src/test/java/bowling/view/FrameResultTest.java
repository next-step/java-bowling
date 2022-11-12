package bowling.view;

import bowling.domain.LastFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임 출력 테스트")
class FrameResultTest {

    @DisplayName("마지막 프레임에서 3개 스트라이크인 경우 표시")
    @Test
    void finalFrame() {

        final LastFrame finalFrame = LastFrame.ready();

        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        assertThat(new FrameResult().frameSign(finalFrame)).contains("X|X|X");
    }

    @DisplayName("마지막 프레임에서 3번 연속 스트라이크인 경우 점수 계산")
    @Test
    void finalFrameScore() {

        final LastFrame finalFrame = LastFrame.ready();

        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        assertThat(new FrameResult().frameScore(finalFrame)).isEqualTo(30);
    }

    @DisplayName("마지막 프레임에서 10|8|/ 경우 점수 계산")
    @Test
    void finalFrameSpare() {

        final LastFrame finalFrame = LastFrame.ready();

        finalFrame.bowl(10);
        finalFrame.bowl(8);
        finalFrame.bowl(2);

        assertThat(new FrameResult().frameScore(finalFrame)).isEqualTo(20);
    }

    @DisplayName("마지막 프레임에서 5|2 MISS 인 경우 점수 계산")
    @Test
    void finalFrameMiss() {

        final LastFrame finalFrame = LastFrame.ready();

        finalFrame.bowl(5);
        finalFrame.bowl(2);

        assertThat(new FrameResult().frameScore(finalFrame)).isEqualTo(7);
    }
}
