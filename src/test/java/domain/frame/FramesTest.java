package domain.frame;

import domain.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.Pins.GUTTER_PINS;
import static domain.Pins.STRIKE_PINS;
import static domain.frame.FrameIndex.MINIMUM_FRAME_INDEX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        List<Frame> testFrames = Arrays.asList(NormalFrame.initFrame());
        frames = Frames.from(new ArrayList<>(testFrames));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void 게임이_끝났는데_게임을_진행하면_예외가_발생한다(int fallenPins) {
        //given
        Pins strike = Pins.STRIKE;
        for (int i = 0; i < 12; i++) {
            frames.play(strike);
        }

        //when
        //then
        assertThatExceptionOfType(GameOverException.class)
                .isThrownBy(() -> {
                    frames.play(Pins.from(fallenPins));
                });
    }

    @Test
    void 프레임이_Closed_되면_다음_인덱스의_프레임을_생성한다() {
        for (int i = 0; i < 10; i++) {
            //then
            assertThat(frames.currentFrame()
                    .equals(frames.getFrames().get(i)))
                    .isTrue();

            //when
            frames.play(Pins.STRIKE);
        }
    }

    @Test
    void 프레임이_Closed_되지_않으면_현재_인덱스의_프레임을_업데이트한다() {
        //when
        frames.play(Pins.GUTTER);

        //then
        assertThat(frames.currentFrame().getIndex())
                .isEqualTo(FrameIndex.from(MINIMUM_FRAME_INDEX));
    }
}
