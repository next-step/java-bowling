package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTypeTest {
    @DisplayName("쓰러트린 핀의 합계가 30개 이상일 경우 예외를 반환한다.")
    @Test
    void overThirtyPin() {
        FrameType frameType = new FinalFrameType();

        assertThatThrownBy(() -> {
            frameType.pitch(10);
            frameType.pitch(10);
            frameType.pitch(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크의 경우 프레임을 진행한다.")
    @Test
    void isContinue_strike() {
        FrameType frameType = new FinalFrameType();
        frameType.pitch(10);

        assertThat(frameType.isContinue()).isTrue();
    }

    @DisplayName("스페어의 경우 프레임을 진행한다.")
    @Test
    void isContinue_spare() {
        FrameType frameType = new FinalFrameType();
        frameType.pitch(8);
        frameType.pitch(2);

        assertThat(frameType.isContinue()).isTrue();
    }

    @DisplayName("세번째 투구까지 완료한 경우 프레임을 종료한다.")
    @Test
    void isContinue_last() {
        FrameType frameType = new FinalFrameType();
        frameType.pitch(7);
        frameType.pitch(3);
        frameType.pitch(10);

        assertThat(frameType.isContinue()).isFalse();
    }

    @DisplayName("첫번째 투구한 경우 프레임을 진행한다.")
    @Test
    void isContinue_firstPitch() {
        FrameType frameType = new FinalFrameType();
        frameType.pitch(8);

        assertThat(frameType.isContinue()).isTrue();
    }

    @DisplayName("두번째 투구가 오픈의 경우 프레임을 종료한다.")
    @Test
    void isContinue_open() {
        FrameType frameType = new FinalFrameType();
        frameType.pitch(1);
        frameType.pitch(3);

        assertThat(frameType.isContinue()).isFalse();
    }
}
