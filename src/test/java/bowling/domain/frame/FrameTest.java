package bowling.domain.frame;

import bowling.domain.State.State;
import bowling.domain.State.StatesTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.State.PinTest.TEN;
import static bowling.domain.State.StateTest.FIRST;
import static bowling.domain.State.StateTest.GUTTER;
import static bowling.domain.State.StateTest.MISS;
import static bowling.domain.State.StateTest.READY;
import static bowling.domain.State.StateTest.SECOND;
import static bowling.domain.State.StateTest.SPARE;
import static bowling.domain.State.StateTest.STRIKE;
import static bowling.domain.frame.FrameNumberTest.MAX_FRAME_NUMBER;
import static bowling.domain.frame.FrameNumberTest.MIN_FRAME_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {
    static final Frame INITIAL_FRAME = Frame.initialize();
    static final Frame LAST_FRAME = new FinalFrame(new FrameNumber(FrameNumber.MAX));
    static final Frame LAST_DONE_FRAME = new FinalFrame(new FrameNumber(FrameNumber.MAX), StatesTest.DONE);

    @Test
    void Frame은_FrameNumber없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new NormalFrame(null, State.ready());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Frame은_State없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new NormalFrame(MIN_FRAME_NUMBER, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {FrameNumber.MAX_IN_NORMAL_FRAME + 1, FrameNumber.MIN - 1})
    void NormalFrame은_범위_밖_frameNumber로_생성_될_경우_예외를_발생_시킨다(int frameNumber) {
        assertThatThrownBy(() -> {
            new NormalFrame(new FrameNumber(frameNumber), State.ready());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {FrameNumber.MAX + 1, FrameNumber.MAX - 1})
    void FinalFrame은_범위_밖_frameNumber로_생성_될_경우_예외를_발생_시킨다(int frameNumber) {
        assertThatThrownBy(() -> {
            new FinalFrame(new FrameNumber(frameNumber));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void next는_다음_Frame을_반환한다() {
        Frame frame = new NormalFrame(new FrameNumber(FrameNumber.MAX - 1), State.ready());

        Frame nextFrame = frame.next();

        assertThat(nextFrame).isInstanceOf(FinalFrame.class);
    }

    @Test
    void isFinal은_마지막_프레임_여부를_반환한다() {
        assertAll(
                () -> assertTrue(INITIAL_FRAME.isNormal()),
                () -> assertFalse(LAST_FRAME.isNormal())
        );
    }

    @Test
    void isDone은_프레임_종료_여부를_반환한다() {
        assertAll(
                () -> assertFalse(new NormalFrame(MIN_FRAME_NUMBER, READY).isDone()),
                () -> assertFalse(new NormalFrame(MIN_FRAME_NUMBER, FIRST).isDone()),
                () -> assertFalse(new NormalFrame(MIN_FRAME_NUMBER, GUTTER).isDone()),
                () -> assertTrue(new NormalFrame(MIN_FRAME_NUMBER, MISS).isDone()),
                () -> assertTrue(new NormalFrame(MIN_FRAME_NUMBER, SECOND).isDone()),
                () -> assertTrue(new NormalFrame(MIN_FRAME_NUMBER, SPARE).isDone()),
                () -> assertTrue(new NormalFrame(MIN_FRAME_NUMBER, STRIKE).isDone()),
                () -> assertTrue(new FinalFrame(MAX_FRAME_NUMBER, StatesTest.DONE).isDone()),
                () -> assertFalse(new FinalFrame(MAX_FRAME_NUMBER, StatesTest.DOING).isDone())
        );
    }

    @Test
    void bowl은_상태를_변경시키고_다음_프레임을_반환한다() {
        Frame frame = INITIAL_FRAME;

        Frame nextFrame = frame.bowl(TEN);

        assertAll(
                () -> assertTrue(frame.isDone()),
                () -> assertThat(nextFrame).isInstanceOf(Frame.class)
        );
    }
}
