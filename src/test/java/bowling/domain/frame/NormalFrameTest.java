package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.domain.score.Pin;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 frame을 생성할 수 있다.")
    void createFirstFrameTest() {

        // given
        Frame excepted = NormalFrame.from(1, null, new Ready());

        // when
        Frame result = NormalFrame.createFirstFrame();

        // then
        assertThat(result).isEqualTo(excepted);
    }

    @Nested
    @DisplayName("현재 state가 완료상태인지 확인할 수 있다.")
    class checkFinishedTest {

        @Test
        @DisplayName("running인 경우 false")
        void finishedFalseTest() {

            // given
            Frame frame = NormalFrame.from(1, null, new Ready());

            // when
            boolean result = frame.isFinishState();

            // then
            assertFalse(result);
        }

        @Test
        @DisplayName("running인 경우 true")
        void finishedTrueTest() {

            // given
            Frame frame = NormalFrame.from(1, null, new Strike(Pin.of(10)));

            // when
            boolean result = frame.isFinishState();

            // then
            assertTrue(result);
        }
    }

    @Test
    @DisplayName("pin을 받아서 state 상태를 업데이트할 수 있다.")
    void updateStateTest() {

        // given
        Frame frame = NormalFrame.createFirstFrame();
        Pin pin = Pin.of(10);

        // when
        frame.updateStateByPin(pin);

        // then
        assertTrue(frame.isFinishState());
    }

    @Test
    @DisplayName("다음 Frame을 생성할 수 있다.")
    void createNextFrameTest() {

        // given
        Frame frame = NormalFrame.from(1, null, new Ready());

        Frame expected = NormalFrame.from(2, null, new Ready());

        // when
        Frame result = frame.createNextFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 frame이 마지막 frame인 경우 마지막 frame을 생성해야 한다.")
    void createNextFinalFrameTest() {

        // given
        Frame frame = NormalFrame.from(9, null, new Ready());

        Frame expected = FinalFrame.of(new Ready());

        // when
        Frame result = frame.createNextFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

}