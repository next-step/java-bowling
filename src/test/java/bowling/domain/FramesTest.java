package bowling.domain;

import bowling.exception.InvalidScoreException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FramesTest {

    @DisplayName("Frames 1번 프레임 한번 투구 - 프레임 안끝남 테스트")
    @Test
    public void pitching_once() {
        Frames frames = new Frames();
        int frameNumber = 1;

        frames.throwBalls(9);
        frameNumber = frames.nextFrameNumber();

        assertThat(frameNumber).isEqualTo(1);
    }

    @DisplayName("Frames 1번 프레임 두번 투구 - 첫번째 프레임 끝 테스트")
    @Test
    public void pitching_twice() {
        Frames frames = new Frames();
        int frameNumber = 1;

        frames.throwBalls(1);
        frames.throwBalls(1);
        frameNumber = frames.nextFrameNumber();

        assertThat(frameNumber).isEqualTo(2);
    }

    @DisplayName("Frames 투구 실패 (핀 갯수 올바르지 않음)")
    @Test
    public void pitching_error() {
        Frames frames = new Frames();

        assertThatThrownBy(() -> frames.throwBalls(11))
                .isInstanceOf(InvalidScoreException.class);
    }

}
