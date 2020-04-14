package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    private Frames frames;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("jjy");
        frames = new Frames(player);
    }

    @Test
    @DisplayName("기본세팅 테스트")
    void sizeFramesTest() {
        assertThat(
                frames.getFrames().size()
        ).isEqualTo(10);
    }

    @Test
    @DisplayName("다음 시작할 프레임 가져오기 테스트")
    void getNextFrameTest() {
        Frames frames = new Frames(player);
        frames.getNextFrame().throwBall(10);
        frames.getNextFrame().throwBall(10);
        frames.getNextFrame().throwBall(10);

        List<Frame> frameList = frames.getFrames();

        assertThat(
                frames.getNextFrame()
        ).isEqualTo(frameList.get(3));
    }
}
