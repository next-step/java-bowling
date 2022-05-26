package bowling.domain.frame;

import bowling.domain.bowl.Bowls;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.frame.FrameNumberTest.MIN_FRAME_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {
    static final Frame INITIAL_FRAME = Frame.initialize();
    static final Frame LAST_FRAME = new FinalFrame(new FrameNumber(FrameNumber.MAX), Bowls.initialize());

    @Test
    void Frame은_FrameNumber없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new NormalFrame(null, Bowls.initialize());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Frame은_Bowls없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new NormalFrame(MIN_FRAME_NUMBER, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {FrameNumber.MAX_IN_NORMAL_FRAME + 1, FrameNumber.MIN - 1})
    void NormalFrame은_범위_밖_frameNumber로_생성_될_경우_예외를_발생_시킨다(int frameNumber) {
        assertThatThrownBy(() -> {
            new NormalFrame(new FrameNumber(frameNumber), Bowls.initialize());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {FrameNumber.MAX + 1, FrameNumber.MAX - 1})
    void FinalFrame은_범위_밖_frameNumber로_생성_될_경우_예외를_발생_시킨다(int frameNumber) {
        assertThatThrownBy(() -> {
            new FinalFrame(new FrameNumber(frameNumber), Bowls.initialize());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void next는_다음_Frame을_반환한다() {
        Frame frame = new NormalFrame(new FrameNumber(FrameNumber.MAX - 1), Bowls.initialize());

        Frame nextFrame = frame.next();

        assertThat(nextFrame).isInstanceOf(FinalFrame.class);
    }

    @Test
    void isFinal은_마지막_프레임_여부를_반환한다() {
        assertAll(
                () -> assertFalse(INITIAL_FRAME.isFinal()),
                () -> assertTrue(LAST_FRAME.isFinal())
        );
    }
}
