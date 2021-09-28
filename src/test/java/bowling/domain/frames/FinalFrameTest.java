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
        AbstractFrame abstractFrame = new FinalFrame();
        abstractFrame.roll(Score.EIGHT);
        assertThrows(IncorrectNumberOfPinsException.class, () -> abstractFrame.roll(Score.TEN));
    }

    @Test
    @DisplayName("투구 - 스트라이크 하나면 begin")
    void roll_finish_one_strike() {
        AbstractFrame abstractFrame = new FinalFrame();
        abstractFrame.roll(Score.TEN);
        assertFalse(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스트라이크 두개면 begin")
    void roll_finish_two_strike() {
        AbstractFrame abstractFrame = new FinalFrame();
        abstractFrame.roll(Score.TEN);
        abstractFrame.roll(Score.TEN);
        assertFalse(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스트라이크 세개면 finish")
    void roll_finish_three_strike() {
        AbstractFrame abstractFrame = new FinalFrame();
        abstractFrame.roll(Score.TEN);
        abstractFrame.roll(Score.TEN);
        abstractFrame.roll(Score.TEN);
        assertTrue(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스페어면 begin")
    void roll_begin_spare() {
        AbstractFrame abstractFrame = new FinalFrame();
        abstractFrame.roll(Score.ONE);
        abstractFrame.roll(Score.NINE);
        assertFalse(abstractFrame.isFinish());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ONE,NINE,TWO", "ONE,NINE,TEN"})
    @DisplayName("투구 - 스페어이후 투구하면 finish")
    void roll_finish_spare(final String scores) {
        AbstractFrame abstractFrame = new FinalFrame();
        for (String score : scores.split(",")) {
            abstractFrame.roll(Score.valueOf(score));
        }
        assertTrue(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - 스페어나 스트라이크가 아니면 finish")
    void roll_finish() {
        AbstractFrame abstractFrame = new FinalFrame();
        abstractFrame.roll(Score.ONE);
        abstractFrame.roll(Score.ONE);
        assertTrue(abstractFrame.isFinish());
    }

    @Test
    @DisplayName("투구 - finish 일때 투구하면 이미 종료된 프레임 에러")
    void roll_finish_exception() {
        AbstractFrame abstractFrame = new FinalFrame(true);
        assertThrows(FinishFrameException.class, () -> abstractFrame.roll(Score.ONE));
    }

    @Test
    @DisplayName("투구 - 안던졌으면 begin")
    void roll_finish_beforeRoll() {
        AbstractFrame abstractFrame = new FinalFrame();
        assertFalse(abstractFrame.isFinish());
    }
}
