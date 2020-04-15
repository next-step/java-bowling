package bowling.domain.frame.state;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GutterTest {

    @DisplayName("gutter 상태에서는 투구 불가능하다")
    @Test
    public void bowl_fail() throws Exception {
        //given
        Gutter gutter = new Gutter();

        //then
        assertThatThrownBy(
                () -> gutter.bowl(0)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("gutter 상태는 완료 상태이다")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Gutter gutter = new Gutter();

        //then
        assertTrue(gutter.isFinish());
    }
}
