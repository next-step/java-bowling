package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishFrameException;
import bowling.domain.exception.IncorrectNumberOfPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void roll_finish_spare() {
        Frame frame = new FinalFrame();
        frame.roll(Score.ONE);
        frame.roll(Score.NINE);
        assertFalse(frame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스페어나 스트라이크가 아니면 finish")
    void roll_finish() {
        Frame frame = new FinalFrame();
        frame.roll(Score.ONE);
        frame.roll(Score.ONE);
        assertTrue(frame.isFinish());
    }

    @Test
    @DisplayName("투구 - finish 일때 투구하면 이미 종료된 프레임 에러")
    void roll_finish_exception() {
        Frame frame = new FinalFrame(true);
        assertThrows(FinishFrameException.class, () -> frame.roll(Score.ONE));
    }

    @Test
    @DisplayName("투구 - 안던졌으면 begin")
    void roll_finish_beforeRoll() {
        Frame frame = new FinalFrame();
        assertFalse(frame.isFinish());
    }
}
