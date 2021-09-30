package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishFrameException;
import bowling.domain.exception.IncorrectNumberOfPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertEquals(new FinalFrame(), new FinalFrame());
    }

    @Test
    @DisplayName("투구 - 두번째 투구까지 투스트라이크가 아닌데 10점이 넘으면 에러 발생")
    void roll_exception() {
        Frame frame = new FinalFrame();
        frame.roll(Score.EIGHT);
        assertThrows(IncorrectNumberOfPinsException.class, () -> frame.roll(Score.TEN));
    }

    @Test
    @DisplayName("투구 - 스트라이크 하나면 begin")
    void roll_finish_one_strike() {
        Frame frame = new FinalFrame();
        frame.roll(Score.TEN);
        assertFalse(frame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스트라이크 두개면 begin")
    void roll_finish_two_strike() {
        Frame frame = new FinalFrame();
        frame.roll(Score.TEN);
        frame.roll(Score.TEN);
        assertFalse(frame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스트라이크 세개면 finish")
    void roll_finish_three_strike() {
        Frame frame = new FinalFrame();
        frame.roll(Score.TEN);
        frame.roll(Score.TEN);
        frame.roll(Score.TEN);
        assertTrue(frame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스페어면 begin")
    void roll_begin_spare() {
        Frame Frame = new FinalFrame();
        Frame.roll(Score.ONE);
        Frame.roll(Score.NINE);
        assertFalse(Frame.isFinish());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ONE,NINE,TWO", "ONE,NINE,TEN"})
    @DisplayName("투구 - 스페어이후 투구하면 finish")
    void roll_finish_spare(final String scores) {
        Frame Frame = new FinalFrame();
        for (String score : scores.split(",")) {
            Frame.roll(Score.valueOf(score));
        }
        assertTrue(Frame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스페어나 스트라이크가 아니면 finish")
    void roll_finish() {
        Frame Frame = new FinalFrame();
        Frame.roll(Score.ONE);
        Frame.roll(Score.ONE);
        assertTrue(Frame.isFinish());
    }

    @Test
    @DisplayName("투구 - finish 일때 투구하면 이미 종료된 프레임 에러")
    void roll_finish_exception() {
        Frame Frame = new FinalFrame(true);
        assertThrows(FinishFrameException.class, () -> Frame.roll(Score.ONE));
    }

    @Test
    @DisplayName("투구 - 안던졌으면 begin")
    void roll_finish_beforeRoll() {
        Frame Frame = new FinalFrame();
        assertFalse(Frame.isFinish());
    }
}
