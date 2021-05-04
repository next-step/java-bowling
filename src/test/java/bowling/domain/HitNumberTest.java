package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HitNumberTest {

    @Test
    void 투구횟수는0_10사이() {
        assertThatThrownBy(() -> {
            HitNumber.of(-1);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            HitNumber.of(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 투구를치다() {
        HitNumber rollNumber = HitNumber.of(3);
        assertThat(rollNumber.hit(4)).isEqualTo(4-3);
    }

    @Test
    void 치고난횟수는_음수가아니다() {
        HitNumber rollNumber = HitNumber.of(3);
        assertThatThrownBy(() -> {
            rollNumber.hit(1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
