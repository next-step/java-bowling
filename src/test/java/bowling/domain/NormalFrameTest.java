package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    void 점수_미스() {
        NormalFrame frame = new NormalFrame(1);
        frame
        .bowl(2)
        .bowl(5);
        assertThat(frame.getNo()).isEqualTo(1);
        assertThat(frame.getScore()).isEqualTo(new Score(7, 0));
    }

    @Test
    void 점수_스트라이크() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(10)
                .bowl(1)
                .bowl(1);

        assertThat(frame.getScore()).isEqualTo(new Score(12, 0));
    }

    @Test
    void 점수_스트라이크_계산실패() {
        NormalFrame frame = new NormalFrame(1);
        Frame frame2 = frame.bowl(10)
                .bowl(1);

        assertThat(frame2.getNo()).isEqualTo(2);
        assertThatThrownBy(() -> frame.getScore()).isInstanceOf(CannotCalculateException.class);
    }

    @Test
    void 점수_스페어() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(4)
                .bowl(6)
                .bowl(1);

        assertThat(frame.getScore()).isEqualTo(new Score(11, 0));
    }

    @Test
    void 점수_스트라이크_9라운드() {
        NormalFrame frame = new NormalFrame(9);
        Frame last = frame.bowl(10)
                .bowl(5)
                .bowl(3);

        assertThat(last.getNo()).isEqualTo(10);
        assertThat(last.isFinish()).isTrue();
        assertThat(frame.getScore()).isEqualTo(new Score(18, 0));
    }
}

