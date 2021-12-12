package bowling.domain.frame;

import bowling.domain.result.FrameResult;
import bowling.domain.result.FrameResults;
import bowling.domain.state.FirstBowl;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames ready;

    @BeforeEach
    void setUp() {
        ready = Frames.readyFrames();
    }

    @DisplayName("생성 테스트")
    @Test
    void createTest() {
        NormalFrame frame = NormalFrame.of(Round.FIRST, Ready.getInstance());
        assertThat(ready).isEqualTo(Frames.of(frame, frame));
    }

    @DisplayName("마지막 frame의 isGameEnd를 반환한다.")
    @Test
    void isEndGameTest() {
        assertThat(ready.isGameEnd()).isFalse();
    }

    @DisplayName("bowl(pin) 마지막 프레임을 투구 한다.")
    @Test
    void bowlTest() {
        ready.bowl(Pin.from(5));
        NormalFrame frame = NormalFrame.of(Round.FIRST, new FirstBowl(Pin.from(5)));
        assertThat(ready)
                .isEqualTo(Frames.of(frame, frame));
    }

    @DisplayName("bowl(pin) 호출 후 다음 프레임으로 넘어가면 frames에 추가 된다.")
    @Test
    void bowlNextTest() {
        ready.bowl(Pin.from(10));
        assertThat(ready)
                .isEqualTo(Frames.of(
                        NormalFrame.of(Round.FIRST, new Strike()),
                        NormalFrame.readyFrame(Round.from(2))
                ));
    }

    @DisplayName("createResults() Frame들의 결과를 FrameResults로 반환한다.")
    @Test
    void createResultsTest() {
        ready.bowl(Pin.TEN).bowl(Pin.from(5));

        FrameResults expect = new FrameResults(Arrays.asList(new FrameResult("X"), new FrameResult("5")));
        assertThat(ready.createResults()).isEqualTo(expect);

    }

    @DisplayName("round() 현재 라운드를 반환한다.")
    @Test
    void roundTest() {
        assertThat(ready.round()).isEqualTo(Round.FIRST);
        Frames strike = ready.bowl(Pin.from(10));
        assertThat(strike.round()).isEqualTo(Round.from(2));
    }
}
