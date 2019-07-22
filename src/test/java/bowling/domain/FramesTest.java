package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 13:33
 */
public class FramesTest {
    @DisplayName("모든 게임 종료 - 상태(강제로 12번 스트라이크 처리)")
    @Test
    void 새로운_프레임_추가() {
        Frames frames = new Frames();
        for (int i = 0; i < 12; i++) {
            frames.bowl(10);
        }
        assertThat(frames.bowl(1)).isFalse();
    }

    @DisplayName("마지막 투구 - 상태(강제로 11번 스트라이크 처리)")
    @Test
    void 마지막_투구_가능() {
        Frames frames = new Frames();
        for (int i = 0; i < 11; i++) {
            frames.bowl(10);
        }
        assertThat(frames.bowl(1)).isTrue();
    }
}
