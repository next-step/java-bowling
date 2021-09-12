package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("처음 던진 투구가 스트라이크면 한 프레임이 끝난다.")
    @Test
    void isEnd_strike() {
        Frame normal = new NormalFrame(1);
        normal.pitch(10);
        assertThat(normal.isEnd()).isTrue();
    }

    @DisplayName("두번째 투구를 마쳤을때 한 프레임이 끝난다.")
    @Test
    void isEnd_secondPitch() {
        Frame normal = new NormalFrame(1);
        normal.pitch(2).pitch(3);
        assertThat(normal.isEnd()).isTrue();
    }


    @Test
    void sumPitches() {
    }
}