package bowling.frame;

import bowling.pin.Pin;
import bowling.state.Ready;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class NormalFrameTest {
    private static final int LIMIT_FRAME_OF_BOWLING_GAME = 10;

    private NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = NormalFrame.init(Ready.init()); // 새로운 프레임은 항상 준비상태로 시작
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("Strike, Spare, Miss를 각각 시뮬레이션 한다")
    void play(final Pin firstDownedPins, final Pin lastDownedPins, final boolean expected) throws Exception {
        pitch(firstDownedPins, lastDownedPins);
        Assertions.assertThat(frame.hasTurn()).isEqualTo(expected);
    }

    private void pitch(final Pin firstDownedPins, final Pin lastDownedPins) {
        frame.play(firstDownedPins);
        if (!frame.hasTurn()) {
            frame.play(lastDownedPins);
        }
    }

    private static Stream<Arguments> play() {
        return Stream.of(
                Arguments.of(Pin.from(Pin.MAX_COUNT_OF_PIN), null, true), // strike
                Arguments.of(Pin.from(5), Pin.from(5), true), // spare
                Arguments.of(Pin.from(5), Pin.from(3), true) // miss
        );
    }

    @Test
    @DisplayName("NormalFrame은 9회까지 진행되고, 10회는 LastFrame이 생성되어야 한다")
    void proceed() throws Exception {
        // given
        Frame frame;
        final Pin strike = Pin.from(Pin.MAX_COUNT_OF_PIN);
        final List<Frame> frames = framesInit();
        Assertions.assertThat(frames.size()).isEqualTo(1);

        // when & then : 1~9프레임 (NormalFrame)
        for (int stage = 2; stage < 11; stage++) {
            frame = getLastFrame(frames);
            play(frame, strike, frames);
            Assertions.assertThat(frames.size()).isEqualTo(stage);
            Assertions.assertThat(frame).isInstanceOf(NormalFrame.class);
        }

        // when & then : 10프레임 (LastFrame)
        Assertions.assertThat(frames.size()).isEqualTo(10);
        Assertions.assertThat(getLastFrame(frames)).isInstanceOf(LastFrame.class);
    }

    private void play(final Frame frame, final Pin strike, final List<Frame> frames) {
        frame.play(strike);
        if (frame instanceof NormalFrame) {
            ((NormalFrame) frame).proceed(frames);
        }
    }

    private Frame getLastFrame(final List<Frame> frames) {
        return frames.get(frames.size() - 1);
    }

    private List<Frame> framesInit() {
        final List<Frame> frames = new ArrayList<>(LIMIT_FRAME_OF_BOWLING_GAME);
        frames.add(NormalFrame.init(Ready.init()));
        return frames;
    }
}
