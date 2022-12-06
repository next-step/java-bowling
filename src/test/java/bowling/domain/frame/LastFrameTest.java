package bowling.domain.frame;

import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by seungwoo.song on 2022-11-30
 */
class LastFrameTest {

    @Test
    void 점수계산_스트라이크() {
        Frame frame = new LastFrame();
        frame.bowl(10);
        frame.bowl(10);
        frame.bowl(10);

        assertThat(frame.getScore().getValue()).isEqualTo(30);
        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 점수계산_스페어() {
        Frame frame = new LastFrame();
        frame.bowl(4);
        frame.bowl(6);
        frame.bowl(10);

        assertThat(frame.getScore().getValue()).isEqualTo(20);
        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 점수계산_미스() {
        Frame frame = new LastFrame();
        frame.bowl(4);
        frame.bowl(5);

        assertThat(frame.getScore().getValue()).isEqualTo(9);
        assertThat(frame.isFinish()).isTrue();
    }

    @Test
    void 점수계산_실패() {
        Frame frame = new LastFrame();
        frame.bowl(4);

        assertThat(frame.isFinish()).isFalse();
        assertThatThrownBy(() -> frame.getScore()).isInstanceOf(CannotCalculateException.class);
    }
}