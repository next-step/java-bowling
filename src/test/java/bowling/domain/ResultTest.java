package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-12-01
 */
class ResultTest {

    @Test
    void 스트라이크_노말() {
        Frame frame = new NormalFrame(1);
        frame.bowl(10)
                .bowl(1)
                .bowl(1);

        Result result = frame.createResult();

        assertThat(result.getScore()).isEqualTo(12);
        assertThat(result.getDesc()).isEqualTo("X");
    }

    @Test
    void 스페어_노말() {
        Frame frame = new NormalFrame(1);
        frame.bowl(4)
                .bowl(6)
                .bowl(1);
        Result result = frame.createResult();

        assertThat(result.getScore()).isEqualTo(11);
        assertThat(result.getDesc()).isEqualTo("4|/");
    }

    @Test
    void 미스_노말() {
        Frame frame = new NormalFrame(1);
        frame.bowl(4).bowl(3);
        Result result = frame.createResult();

        assertThat(result.getScore()).isEqualTo(7);
        assertThat(result.getDesc()).isEqualTo("4|3");
    }

    @Test
    void 마지막_미스() {
        Frame frame = new LastFrame();
        frame.bowl(3)
                .bowl(2);
        Result result = frame.createResult();

        assertThat(frame.isFinish()).isTrue();
        assertThat(result.getScore()).isEqualTo(5);
        assertThat(result.getDesc()).isEqualTo("3|2");
    }

    @Test
    void 마지막_3스트라이크() {
        Frame frame = new LastFrame();
        frame.bowl(10)
                .bowl(10)
                .bowl(10);
        Result result = frame.createResult();

        assertThat(frame.isFinish()).isTrue();
        assertThat(result.getScore()).isEqualTo(30);
        assertThat(result.getDesc()).isEqualTo("X|X|X");
    }

    @Test
    void 마지막_스페어() {
        Frame frame = new LastFrame();
        frame.bowl(4)
                .bowl(6)
                .bowl(10);
        Result result = frame.createResult();

        assertThat(frame.isFinish()).isTrue();
        assertThat(result.getScore()).isEqualTo(20);
        assertThat(result.getDesc()).isEqualTo("4|/|X");
    }
}

