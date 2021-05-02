package bowling.domain.status;

import bowling.domain.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {
    @DisplayName("미스 상태 후 투구가 이뤄지면 예외를 반환한다.")
    @Test
    void roll_strike() {
        Status miss = new Miss(new Pitch(6));
        assertThatThrownBy(() -> miss.roll(10)).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Miss의 보너스 투구 가능여부는 거짓을 반환한다.")
    @Test
    void hasBonusPitch() {
        Status miss = new Miss(new Pitch(6));
        assertThat(miss.hasBonusPitch()).isFalse();
    }
}
