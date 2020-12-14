package bowling.frame;

import bowling.frame.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.from(new LinkedList<>());
    }

    @Test
    @DisplayName("Frames 에서 투구 시 State Strike 와 다음 프레임 추가 확인")
    void bowl() {
        Frame frame = frames.bowl("10");
        assertThat(frame.getState() instanceof Strike).isTrue();
        assertThat(frames.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음 프레임으로 이동 및 프레임 생성 - 노말 프레임")
    void playNextIsNormalFrame() {
        Frame frame = frames.next(6);
        assertThat(frame instanceof NormalFrame).isTrue();
    }

    @Test
    @DisplayName("다음 프레임으로 이동 및 프레임 생성 - 파이널 프레임")
    void playNextIsFinalFrame() {
        Frame frame = frames.next(10);
        assertThat(frame instanceof FinalFrame).isTrue();
    }

    @Test
    @DisplayName("다음 프레임 추가")
    void addNextFrame() {
        frames.addNextFrame(2);
        assertThat(frames.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음 프레임 이동 가능 여부 확인 - 성공")
    void moveNextFrameIsTrue() {
        frames.addNextFrame(9);
        assertThat(frames.canMoveNextFrame()).isTrue();
    }

    @Test
    @DisplayName("다음 프레임 이동 가능 여부 확인 - 실패")
    void moveNextFrameIsFalse() {
        frames.addNextFrame(11);
        assertThat(frames.canMoveNextFrame()).isFalse();
    }

    @Test
    @DisplayName("현재 프레임이 종료되었는지 확인")
    void isCurrentFrameFinish() {
        frames.addNextFrame(1);
        assertThat(frames.isCurrentFrameFinish(1)).isFalse();
    }

    @Test
    @DisplayName("현재 프레임의 다음 프레임 번호 확인")
    void nextFrameNumber() {
        frames.addNextFrame(1);
        assertThat(frames.getNextFrameNumber()).isEqualTo(2);
    }

}
