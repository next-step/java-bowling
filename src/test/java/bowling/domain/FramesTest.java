package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.exception.GameOverException;

class FramesTest {

    @DisplayName("마지막 프레임이 아닌 프레임에서 스트라이크를 치면 다음 프레임으로 이동한다.")
    @Test
    void frameAfterStrike() {
        Frames frames = new Frames();
        frames.bowl(10);

        Frame currentFrame = frames.current();
        assertThat(currentFrame.number()).isEqualTo(2);
    }

    @DisplayName("마지막 프레임이 아닌 프레임에서 두 번 투구하면 다음 프레임으로 이동한다.")
    @Test
    void frameAfterSpare() {
        Frames frames = new Frames();
        frames.bowl(4);
        frames.bowl(5);

        Frame currentFrame = frames.current();
        assertThat(currentFrame.number()).isEqualTo(2);
    }

    @DisplayName("마지막 프레임에서 두 번의 투구 안에 스트라이크/스페어를 치지 못하면 프레임이 종료된다.")
    @Test
    void lastFrameWithoutBonus() {
        Frames frames = framesProgressedUntil9Frame();
        frames.bowl(3);
        frames.bowl(4);

        Frame lastFrame = frames.of(10);
        assertThat(lastFrame.isEnd()).isTrue();
    }

    @DisplayName("마지막 프레임에서 두 번의 투구 안에 스트라이크를 쳤다면 프레임은 종료되지 않는다.")
    @Test
    void lastFrameWithBonusByStrike() {
        Frames frames = framesProgressedUntil9Frame();
        frames.bowl(10);
        frames.bowl(3);

        Frame lastFrame = frames.of(10);
        assertThat(lastFrame.isEnd()).isFalse();
    }

    @DisplayName("마지막 프레임에서 두 번의 투구 안에 스페어를 쳤다면 프레임은 종료되지 않는다.")
    @Test
    void lastFrameWithBonusBySpare() {
        Frames frames = framesProgressedUntil9Frame();
        frames.bowl(7);
        frames.bowl(3);

        Frame lastFrame = frames.of(10);
        assertThat(lastFrame.isEnd()).isFalse();
    }

    @DisplayName("마지막 프레임이 종료된 상태에서 현재 프레임 조회 시 GameOverException 예외가 발생한다.")
    @Test
    void gameOver() {
        Frames frames = framesProgressedUntil9Frame();
        frames.bowl(5);
        frames.bowl(3);

        assertThat(frames.of(10).isEnd()).isTrue();
        assertThatThrownBy(() -> frames.current())
            .isInstanceOf(GameOverException.class);
    }

    private Frames framesProgressedUntil9Frame() {
        Frames frames = new Frames();
        for (int i = 1; i <= 9; i++) {
            frames.bowl(10);
        }
        return frames;
    }

}
