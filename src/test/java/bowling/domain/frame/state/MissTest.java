package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("miss 상태는 공을 굴릴수 없다")
    @Test
    public void bowl_fail() throws Exception {
        //given
        Miss miss = new Miss(new Pins(1), new Pins(2));

        //then
        assertThatThrownBy(
                () -> miss.bowl(1)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("miss 상태는 마무리 상태이다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Miss miss = new Miss(new Pins(1), new Pins(2));

        //then
        assertTrue(miss.isFinish());
    }
}
