package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishFrameException;
import bowling.domain.exception.IncorrectNumberOfPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertEquals(new NormalFrame(), new NormalFrame());
    }

    @Test
    @DisplayName("투구 - 점수 합산이 10점이 넘으면 에러 발생")
    void roll_exception() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.roll(Score.EIGHT);
        assertThrows(IncorrectNumberOfPinsException.class, () -> abstractFrame.roll(Score.TEN));
    }

    @Test
    @DisplayName("투구 - 스트라이크면 finish")
    void roll_finish_strike() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.roll(Score.TEN);
        assertTrue(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스페어면 finish")
    void roll_finish_spare() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.roll(Score.EIGHT);
        abstractFrame.roll(Score.TWO);
        assertTrue(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - finish 일때 투구하면 이미 종료된 프레임 에러")
    void roll_finish_exception() {
        AbstractFrame abstractFrame = new NormalFrame(true);
        assertThrows(FinishFrameException.class, () -> abstractFrame.roll(Score.ONE));
    }

    @Test
    @DisplayName("투구 - 안던졌으면 begin")
    void roll_finish_beforeRoll() {
        AbstractFrame abstractFrame = new NormalFrame();
        assertFalse(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - 2번 던졌으면 finish")
    void roll_finish_twiceRoll() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.roll(Score.ONE);
        abstractFrame.roll(Score.ONE);
        assertTrue(abstractFrame.isFinish());
    }

}