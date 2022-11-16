package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class HitTest {

    @DisplayName("투구가 음수이면, 예외가 발생해야 한다.")
    @Test
    void create_givenNegativeValue() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Hit(-1))
                .withMessage("투구는 0 보다 작을 수 없습니다.");
    }

    @DisplayName("투구가 남은 핀의 개수보다 크면, 예외가 발생해야 한다.")
    @Test
    void create_givenOverRemainedPins() {
        Hit previousHit = new Hit(2);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Hit(9, previousHit))
                .withMessage("투구는 남은 핀의 개수(8) 보다 클 수 없습니다.");
    }

}
