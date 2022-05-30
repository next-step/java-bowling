package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pin;
import bowling.domain.state.State;
import bowling.domain.state.StatesTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.frame.FrameNumberTest.MAX_FRAME_NUMBER;
import static bowling.domain.frame.FrameNumberTest.MIN_FRAME_NUMBER;
import static bowling.domain.score.ScoreTest.SCORABLE;
import static bowling.domain.score.ScoreTest.UNSCORABLE;
import static bowling.domain.state.PinTest.TEN;
import static bowling.domain.state.StateTest.FIRST;
import static bowling.domain.state.StateTest.GUTTER;
import static bowling.domain.state.StateTest.MISS;
import static bowling.domain.state.StateTest.READY;
import static bowling.domain.state.StateTest.SECOND;
import static bowling.domain.state.StateTest.SPARE;
import static bowling.domain.state.StateTest.STRIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {
    static final Frame INITIAL_FRAME = Frame.initialize();
    static final Frame NORMAL_DONE_FRAME = new NormalFrame(new FrameNumber(FrameNumber.MIN), SECOND);
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

    @Test
    void score는_Score를_반환한다() {
        assertAll(
                () -> assertInstanceOf(Score.class, NORMAL_DONE_FRAME.score()),
                () -> assertInstanceOf(Score.class, LAST_DONE_FRAME.score()),
                () -> assertTrue(NORMAL_DONE_FRAME.score().canScore()),
                () -> assertTrue(LAST_DONE_FRAME.score().canScore()),
                () -> assertFalse(INITIAL_FRAME.score().canScore()),
                () -> assertFalse(LAST_FRAME.score().canScore())
        );
    }

    @Test
    void score는_증가된_Score를_반환한다() {
        assertAll(
                () -> assertInstanceOf(Score.class, NORMAL_DONE_FRAME.score(SCORABLE)),
                () -> assertInstanceOf(Score.class, LAST_DONE_FRAME.score(SCORABLE)),
                () -> assertTrue(NORMAL_DONE_FRAME.score(SCORABLE).canScore()),
                () -> assertTrue(LAST_DONE_FRAME.score(UNSCORABLE).canScore()),
                () -> assertFalse(INITIAL_FRAME.score(Score.strike(new Pin(0))).canScore())
        );
    }
}
