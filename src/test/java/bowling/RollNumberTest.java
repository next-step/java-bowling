package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RollNumberTest {

    @Test
    void 투구횟수는0_10사이() {
        assertThatThrownBy(() -> {
            RollNumber.of(-1);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            RollNumber.of(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 투구를치다() {
        RollNumber rollNumber = RollNumber.of(3);
        assertThat(rollNumber.hit(4)).isEqualTo(4-3);
    }

    @Test
    void 치고난횟수는_음수가아니다() {
        RollNumber rollNumber = RollNumber.of(3);
        assertThat(rollNumber.hit(1)).isEqualTo(0);
    }


}
