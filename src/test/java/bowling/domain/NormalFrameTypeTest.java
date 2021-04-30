package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTypeTest {

    @DisplayName("쓰러트린 핀의 합계가 10개 이상일 경우 예외를 반환한다.")
    @Test
    void overTenPin() {
        FrameType frameType = new NormalFrameType();

        assertThatThrownBy(() -> {
            frameType.pitch(8);
            frameType.pitch(3);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크의 경우 프레임을 종료한다.")
    @Test
    void isContinue_strike() {
        FrameType frameType = new NormalFrameType();
        frameType.pitch(10);

        assertThat(frameType.isContinue()).isFalse();
    }

    @DisplayName("스페어의 경우 프레임을 종료한다.")
    @Test
    void isContinue_spare() {
        FrameType frameType = new NormalFrameType();
        frameType.pitch(8);
        frameType.pitch(2);

        assertThat(frameType.isContinue()).isFalse();
    }

    @DisplayName("두번째 투구까지 완료한 경우 프레임을 종료한다.")
    @Test
    void isContinue_open() {
        FrameType frameType = new NormalFrameType();
        frameType.pitch(7);
        frameType.pitch(2);

        assertThat(frameType.isContinue()).isFalse();
    }

    @DisplayName("첫번째 투구한 경우 프레임을 진행한다.")
    @Test
    void isContinue_firstPitch() {
        FrameType frameType = new NormalFrameType();
        frameType.pitch(8);

        assertThat(frameType.isContinue()).isTrue();
    }
}
