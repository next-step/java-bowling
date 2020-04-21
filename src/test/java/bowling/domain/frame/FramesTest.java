package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("다음 시작할 프레임 생성, 가져오기 테스트")
    void getNextFrameTest() {
        frames.getNextFrame().throwBall(Point.of(10));
        frames.getNextFrame().throwBall(Point.of(10));
        frames.getNextFrame().throwBall(Point.of(10));

        assertThat(
                frames.getLastFrame().points.getSum()
        ).isEqualTo(10);
    }

    @Test
    @DisplayName("마지막 프레임 가져오기 테스트")
    void getLastFrameTest() {
        Frames frames = new Frames(player);
        for (int i = 1; i < 13; i++) {
            frames.getNextFrame().throwBall(Point.of(10));
        }

        assertThat(
                frames.getLastFrame()
        ).isEqualTo(frames.getFrames().get(9));
    }

    @Test
    @DisplayName("프레임이 가득찼는지 테스트")
    void isMaxTest() {
        Frames frames = new Frames(player);
        for (int i = 1; i < 13; i++) {
            frames.getNextFrame().throwBall(Point.of(10));
        }

        assertThat(
                frames.isMax()
        ).isTrue();
    }
}
