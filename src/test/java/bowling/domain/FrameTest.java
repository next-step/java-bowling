package bowling.domain;

import bowling.exception.CannotCalculateException;
import bowling.exception.FrameTryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @DisplayName("스트라이크는 한번만 투구하면 종료된다")
    @Test
    void throwBallStrikeTest() {
        Frame normalFrame = NormalFrame.from();
        normalFrame.throwBall(10);
        assertThat(normalFrame.roundEnded()).isTrue();
    }

    @DisplayName("1~9프레임은 공을 두번 던질 수 있다")
    @Test
    void throwBallNotStrikeTest() {
        Frame normalFrame = NormalFrame.from();
        normalFrame.throwBall(8);
        normalFrame.throwBall(2);
        assertThat(normalFrame.roundEnded()).isTrue();
    }

    @DisplayName("종료된 프레임에서 공을 던질 수 없다")
    @Test
    void throwBallExceptionTest() {
        Frame normalFrame = NormalFrame.from();
        normalFrame.throwBall(7);
        normalFrame.throwBall(1);
        assertThatThrownBy(() -> normalFrame.throwBall(1))
                .isInstanceOf(FrameTryException.class);
    }

    @DisplayName("마지막 프레임에서 첫투구에서 스트이크를 하면 총 3번 던질 수 있다")
    @Test
    void throwBallStrikeInFinalTest() {
        Frame finalFrame = FinalFrame.getFrame();
        finalFrame.throwBall(10);
        finalFrame.throwBall(10);
        finalFrame.throwBall(10);
        assertThat(finalFrame.roundEnded()).isTrue();
    }

    @DisplayName("마지막 프레임에서 스페어 처리를 하면 총 3번 던질 수 있다")
    @Test
    void throwBallSpareInFinalTest() {
        Frame finalFrame = FinalFrame.getFrame();
        finalFrame.throwBall(8);
        finalFrame.throwBall(2);
        finalFrame.throwBall(9);
        assertThat(finalFrame.roundEnded()).isTrue();
    }

    @DisplayName("프레임이 아직 시작 안한지 확인한다")
    @Test
    void frameNotYetTest() {
        Frame frame = NormalFrame.from();
        assertThat(frame.isNotYetStart()).isTrue();
    }

    @DisplayName("일반 프레임의 2 투구의 점수를 계산한다")
    @Test
    void normalFrameGetScoreTest() {
        Frames frames = Frames.init();
        frames.throwBall(5);
        frames.throwBall(4);
        Frame normalFrame = frames.nFrame(0);
        assertThat(normalFrame.getScore()).isEqualTo(9);
    }

    @DisplayName("프레임이 스트라이크 혹은 스페어라면 점수를 계산할 수 없다")
    @Test
    void normalFrameGetScoreExceptionTest() {
        Frames strikeFrames = Frames.init();
        strikeFrames.throwBall(10);
        Frame strikeFrame = strikeFrames.nFrame(0);
        Frames spareFrames = Frames.init();
        spareFrames.throwBall(9);
        spareFrames.throwBall(1);
        Frame spareFrame = spareFrames.nFrame(0);
        assertThatThrownBy(() -> {
            strikeFrame.getScore();
            spareFrame.getScore();
        }).isInstanceOf(CannotCalculateException.class);
    }

    @DisplayName("스트라이크 시 뒤의 2 프레임의 점수를 더한 값을 얻는다")
    @Test
    void frameGetScoreWhenStrikeTest() {
        Frames frames = Frames.init();
        frames.throwBall(10);
        frames.throwBall(2);
        frames.throwBall(7);
        Frame firstFrame = frames.nFrame(0);
        assertThat(firstFrame.getScore()).isEqualTo(19);
    }

    @DisplayName("스페어 시 뒤의 1 프레임의 점수를 더한 값을 얻는다")
    @Test
    void frameGetScoreWhenSpareTest() {
        Frames frames = Frames.init();
        frames.throwBall(7);
        frames.throwBall(3);
        frames.throwBall(2);
        Frame firstFrame = frames.nFrame(0);
        assertThat(firstFrame.getScore()).isEqualTo(12);
    }
}
