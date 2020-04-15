package bowling.domain.frame;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NormalFrameTest {

    @DisplayName("1~10번 프레임 까지만 등록 가능 하다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void validate_success_frameNumber(int number) throws Exception {
        //then
        new NormalFrame(number);
    }

    @DisplayName("1~10번 외의 프레임 까지만 등록시 exception")
    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 11, 15, 100})
    public void validate_fail(int number) throws Exception {
        //then
        assertThatThrownBy(
                () -> new NormalFrame(number)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("10번째 생성된 프레임은 FinalFrame 이어야 한다")
    @Test
    public void createNext_success() throws Exception {
        //given
        Frame frame = new NormalFrame();

        //when
        for (int i = 0; i < 9; i++) {
            frame = frame.createNext();
            frame = frame.getNext();
        }

        //then
        assertTrue(frame instanceof FinalFrame);
    }

    @DisplayName("남은 핀이 없으면 해당 프레임은 완료 상태 이다")
    @Test
    public void isFinish_success_strike() throws Exception {
        //given
        Frame frame = new NormalFrame();

        //when
        frame = frame.bowl(10);

        //then
        assertTrue(frame.isFinish());
    }

    @DisplayName("남은 핀이 있으면 해당 프레임은 완료 상태가 아니다")
    @Test
    public void isFinish_success_miss() throws Exception {
        //given
        Frame frame = new NormalFrame();

        //when
        frame = frame.bowl(5);

        //then
        assertFalse(frame.isFinish());
    }
}
