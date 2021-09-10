package bowling.model.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("프레임 컬렉션 테스트")
public class FramesTest {
    private Frames firstEndedFrames;
    private Frames secondEndedFrames;

    @BeforeEach
    void setUp() {
        firstEndedFrames = new Frames(Arrays.asList(new NormalFrame(1, 5, 5, 10, 1), new FinalFrame(10, 5, 4, 24, 0, 0)));
        secondEndedFrames = new Frames(Arrays.asList(new NormalFrame(1, 5, 5, 10, 1), new FinalFrame(10, 5, 5, 25, 1, 5)));
    }

    @DisplayName("canPlayNext() 메소드를 통해 다음 플레이 가능 여부를 조회할 수 있다.")
    @Test
    void canPlayNextTest() {
        // given
        Frames emptyFrames = new Frames();

        // when, then
        assertTrue(emptyFrames.canPlayNext());
        assertFalse(firstEndedFrames.canPlayNext());
        assertFalse(secondEndedFrames.canPlayNext());
    }

    @DisplayName("게임을 진행할 수 없는 프레임 상태인데 play를 하면 예외가 발생한다.")
    @Test
    void playExceptionTest() {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstEndedFrames.play(5))
                .withMessage("더 이상 게임을 진행할 수 없습니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> secondEndedFrames.play(5))
                .withMessage("더 이상 게임을 진행할 수 없습니다.");
    }

    @DisplayName("nextFrameNumber()를 통해 다음 프레임의 번호를 조회할 수 있다.")
    @Test
    void nextFrameNumberTest() {
        // given, when, then
        Frames frames = new Frames();
        assertEquals(frames.nextFrameNumber(), new FrameNumber(1));

        frames.play(5);
        assertEquals(frames.nextFrameNumber(), new FrameNumber(1));

        frames.play(5);
        assertEquals(frames.nextFrameNumber(), new FrameNumber(2));

        frames.play(10);
        assertEquals(frames.nextFrameNumber(), new FrameNumber(3));
    }

    @DisplayName("게임을 진행할 수 없는 프레임 상태인데 다음 프레임 번호를 조회하면 예외가 발생한다.")
    @Test
    void nextFrameNumberExceptionTest() {
        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstEndedFrames.nextFrameNumber())
                .withMessage("더 이상 게임을 진행할 수 없습니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> secondEndedFrames.nextFrameNumber())
                .withMessage("더 이상 게임을 진행할 수 없습니다.");
    }
}