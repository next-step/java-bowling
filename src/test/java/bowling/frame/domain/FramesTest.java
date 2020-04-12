package bowling.frame.domain;

import bowling.player.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("한 프레임이 종료되면 다음 프레임을 생성한다")
    @Test
    public void generateFrameTest() {
        Frames frames = Frames.init(Player.of("LJW"));
        frames.bowl(10);
        assertThat(frames.getSize()).isEqualTo(2);
    }

    @DisplayName("모든 프레임이 종료되면 게임이 종료 된다")
    @Test
    public void isDoneTest() {
        Frames frames = Frames.init(Player.of("LJW"));
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        // 10 frame Gutter 라고 가정
        frames.bowl(0);
        frames.bowl(0);
        assertThat(frames.isDone()).isTrue();
    }


}
