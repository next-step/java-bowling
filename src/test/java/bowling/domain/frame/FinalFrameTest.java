package bowling.domain.frame;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("마지막 프레임은 다음 프레임을 get할수 없다")
    @Test
    public void getNext_fail() throws Exception {
        //given
        FinalFrame finalFrame = new FinalFrame();

        //then
        assertThatThrownBy(
                () -> finalFrame.getNext()
        ).isInstanceOf(BowlingException.class);
    }
}
