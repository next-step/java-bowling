package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class FramesTest {
    private Frames frames;
    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(Arrays.asList(new Player("jjy"), new Player("ddd")));
        frames = new Frames();
    }

    @Test
    @DisplayName("다음 시작할 프레임 가져오기 테스트")
    void getNextFrameTest() {
        frames.getNextFrame().throwBall(Point.of(10));
        frames.getNextFrame().throwBall(Point.of(10));
        frames.getNextFrame().throwBall(Point.of(10));

        assertThatCode(
                () -> frames.getLastFrame()
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("마지막 프레임 가져오기 테스트")
    void getLastFrameTest() {
        Frames frames = new Frames();
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
        Frames frames = new Frames();
        for (int i = 1; i < 13; i++) {
            frames.getNextFrame().throwBall(Point.of(10));
        }

        assertThat(
                frames.isMax()
        ).isTrue();
    }
}
