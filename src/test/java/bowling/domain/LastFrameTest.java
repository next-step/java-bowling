package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class LastFrameTest {

    @Test
    void last_frame() {

        assertThatThrownBy(() -> new LastFrame(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("마지막 프레임의 번호를 초과하였습니다.");
    }

    @DisplayName("마지막 프레임에서 스패어 처리를 한다.")
    @Test
    void create_frame() {

        final LastFrame lastFrame = new LastFrame(1);
        lastFrame.pitch(1);
        lastFrame.pitch(9);

        assertAll(
                () -> assertThat(lastFrame.isEmpty()).isFalse(),
                () -> assertThat(lastFrame.canPitch()).isFalse(),
                () -> assertThat(lastFrame.status()).isEqualTo(ScoreType.SPARE),
                () -> assertThat(lastFrame.pinsSize()).isEqualTo(2)
        );
    }
}