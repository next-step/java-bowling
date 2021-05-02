package bowling.domain.status;

import bowling.domain.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HoldTest {
    @DisplayName("두번째 투구에서 스페어 처리를 하면 스페어 객체를 반환한다.")
    @Test
    void roll_strike() {
        Status hold = new Hold(new Pitch(6));
        assertThat(hold.roll(4)).isExactlyInstanceOf(Spare.class);
    }

    @DisplayName("두번째 투구에서 스페어 처리를 못 하면 미스 객체를 반환한다.")
    @Test
    void roll_hold() {
        Status hold = new Hold(new Pitch(3));
        assertThat(hold.roll(8)).isExactlyInstanceOf(Miss.class);
    }

    @DisplayName("Hold의 보너스 투구 가능여부는 거짓을 반환한다.")
    @Test
    void hasBonusPitch() {
        Status hold = new Hold(new Pitch(3));
        assertThat(hold.hasBonusPitch()).isFalse();
    }
}
