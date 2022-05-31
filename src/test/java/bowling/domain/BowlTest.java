package bowling.domain;

import bowling.domain.state.last.ThirdBowl;
import bowling.domain.state.normal.*;
import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlTest {
    private Frame normalFrame;
    private Frame lastFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame();
        lastFrame = new LastFrame();
    }

    @Test
    void 볼링_초기화시_상태_9프레임_이전() {
        assertThat(normalFrame.firstState()).isInstanceOf(Ready.class);
    }

    @Test
    void 볼링_1차_비유효입력시_9프레임_이전() {
        assertThatThrownBy(() -> normalFrame.play(1, 12))
                .isInstanceOf(InvalidNumberOfFallenPinsException.class);
    }

    @Test
    void 볼링_1차_스트라이크시_상태_9프레임_이전() {
        assertThat(normalFrame.play(1, 10).firstState()).isInstanceOf(Strike.class);
    }

    @Test
    void 볼링_1차_거터시_상태_9프레임_이전() {
        assertThat(normalFrame.play(1, 0).firstState()).isInstanceOf(Gutter.class);
    }

    @Test
    void 볼링_1차_1에서_9사이_상태_9프레임_이전() {
        assertThat(normalFrame.play(1, 6).firstState()).isInstanceOf(FirstBowl.class);
    }

    @Test
    void 볼링_2차_스페어시_상태_9프레임_이전() {
        Frame frame = normalFrame.play(1, 6);
        assertThat(frame.play(1, 4).secondState()).isInstanceOf(Spare.class);
    }

    @Test
    void 볼링_2차_1에서_9사이_상태_9프레임_이전() {
        Frame frame = normalFrame.play(1, 6);
        assertThat(frame.play(1, 2).secondState()).isInstanceOf(SecondBowl.class);
    }

    @Test
    void 볼링_2차_이후_게임진행시_9프레임_이전() {
        Frame frame = normalFrame.play(1, 6).play(1, 3);
        assertThatThrownBy(() -> frame.play(1, 1))
                .isInstanceOf(EndedFrameException.class);
    }

    @Test
    void 볼링_초기화시_상태_10프레임() {
        assertThat(lastFrame.firstState()).isInstanceOf(Ready.class);
    }

    @Test
    void 볼링_1차_비유효입력시_10프레임() {
        assertThatThrownBy(() -> lastFrame.play(10, 12))
                .isInstanceOf(InvalidNumberOfFallenPinsException.class);
    }

    @Test
    void 볼링_1차_스트라이크시_상태_10프레임() {
        assertThat(lastFrame.play(10, 10).firstState()).isInstanceOf(Strike.class);
    }

    @Test
    void 볼링_1차_스트라이크_2차_1에서_9사이_상태_10프레임() {
        Frame frame = lastFrame.play(10, 10).play(10, 3);
        assertThat(frame.secondState()).isInstanceOf(bowling.domain.state.last.SecondBowl.class);
    }

    @Test
    void 볼링_1차_스트라이크_2차_스트라이크_상태_10프레임() {
        Frame frame = lastFrame.play(10, 10).play(10, 10);
        assertThat(frame.secondState()).isInstanceOf(Strike.class);
    }

    @Test
    void 볼링_1차_스트라이크_2차_스트라이크_3차_스트라이크_상태_10프레임() {
        Frame frame = lastFrame.play(10, 10).play(10, 10).play(10, 10);
        assertThat(((LastFrame) frame).thirdState()).isInstanceOf(Strike.class);
    }

    @Test
    void 볼링_1차_거터시_상태_10프레임() {
        assertThat(lastFrame.play(10, 0).firstState()).isInstanceOf(Gutter.class);
    }

    @Test
    void 볼링_1차_1에서_9사이_상태_10프레임() {
        assertThat(lastFrame.play(10, 6).firstState()).isInstanceOf(FirstBowl.class);
    }

    @Test
    void 볼링_2차_스페어시_상태_10프레임() {
        Frame frame = lastFrame.play(10, 6);
        assertThat(lastFrame.play(10, 4).secondState()).isInstanceOf(Spare.class);
    }

    @Test
    void 볼링_2차_스페어시_3차_1에서_9사이_상태_10프레임() {
        Frame frame = lastFrame.play(10, 6).play(10, 4);
        assertThat(((LastFrame) frame.play(10, 4)).thirdState()).isInstanceOf(ThirdBowl.class);
    }

    @Test
    void 볼링_2차_스페어시_3차_스트라이크시_상태_10프레임() {
        Frame frame = lastFrame.play(10, 6).play(10, 4);
        assertThat(((LastFrame) lastFrame.play(10, 10)).thirdState()).isInstanceOf(Strike.class);
    }

    @Test
    void 볼링_2차_합산_1에서_9사이_상태_10프레임() {
        Frame frame = lastFrame.play(10, 6);
        assertThat(lastFrame.play(10, 3).secondState()).isInstanceOf(SecondBowl.class);
    }

    @Test
    void 볼링_2차_합산_1에서_9사이_3차_진행시_10프레임() {
        Frame frame = lastFrame.play(10, 6).play(10, 3);
        assertThatThrownBy(() -> frame.play(10, 1))
                .isInstanceOf(EndedFrameException.class);
    }
}