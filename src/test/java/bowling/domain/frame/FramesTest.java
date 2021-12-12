package bowling.domain.frame;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.readyFrames();
    }
//
//    @DisplayName("생성 테스트")
//    @Test
//    void createTest() {
//        LinkedList<Frame> frames = new LinkedList<>();
//        frames.add(NormalFrame.readyFrame(Round.from(1)));
//        assertThat(Frames.from(frames)).isEqualTo(Frames.from(frames));
//        assertThat(Frames.readyFrames()).isEqualTo(Frames.from(frames));
//    }

    @DisplayName("마지막 frame의 isGameEnd를 반환한다.")
    @Test
    void isEndGameTest() {
        assertThat(frames.isGameEnd()).isFalse();
    }

    @DisplayName("bowl(pin) 마지막 프레임을 투구 한다.")
    @Test
    void bowlTest() {
        frames.bowl(Pin.from(5));
        NormalFrame frame = NormalFrame.of(Round.FIRST, new FirstBowl(Pin.from(5)));
        assertThat(frames)
                .isEqualTo(Frames.of(frame, frame));
    }

    @DisplayName("bowl(pin) 호출 후 다음 프레임으로 넘어가면 frames에 추가 된다.")
    @Test
    void bowlNextTest() {
        frames.bowl(Pin.from(10));
        assertThat(frames)
                .isEqualTo(Frames.of(
                        NormalFrame.of(Round.FIRST, new Strike()),
                        NormalFrame.readyFrame(Round.from(2))
                ));
    }
}
