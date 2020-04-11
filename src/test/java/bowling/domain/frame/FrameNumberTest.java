package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameNumberTest {
    @DisplayName("프레임 번호를 생성할 수 있다.")
    @Test
    void create() {
        int number = 1;
        FrameNumber expect = new FrameNumber(number);

        FrameNumber actual = new FrameNumber(number);

        assertThat(actual).isEqualTo(expect);
    }

}