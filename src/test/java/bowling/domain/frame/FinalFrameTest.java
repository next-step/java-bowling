package bowling.domain.frame;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinalFrameTest {

    @DisplayName("마지막 프레임은 다음 프레임을 만들 수 없다")
    @Test
    public void createNext_fail() throws Exception {
        //given
        FinalFrame finalFrame = new FinalFrame();

        //then
        assertThatThrownBy(
                () -> finalFrame.createNext()
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("마지막 프레임은 다음 프레임 생성시 자기 자신을 반환 한다")
    @Test
    public void createNext_success() throws Exception {
        //given
        FinalFrame finalFrame = new FinalFrame();

        //when
        Frame next = finalFrame.getNext();


        //then
        assertTrue(finalFrame.equals(next));
    }

    @DisplayName("완료 상태에서 공을 굴리면 exception")
    @Test
    public void bowl_success() throws Exception {
        //given
        Frame finalFrame = new FinalFrame();

        //when
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);

        //then
        Frame finalFrame1 = finalFrame;
        assertThatThrownBy(
                ()-> finalFrame1.bowl(2)
        ).isInstanceOf(BowlingException.class);
    }
}
