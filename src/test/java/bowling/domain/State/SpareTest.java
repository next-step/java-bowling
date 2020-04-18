package bowling.domain.State;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpareTest {
    @DisplayName("스페어인지 체크한다")
    @Test
    void validateSpare() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Spare(Pins.bowl(1), Pins.bowl(8));
        });
    }

    @DisplayName("첫번째 핀과 두번째 핀에 이후에 또다시 공을 던지려고 하면  예외를 발생시킨다.")
    @Test
    void bowl() {
        assertThatThrownBy(() -> {
            new Spare(Pins.bowl(3), Pins.bowl(7)).bowl(8);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
