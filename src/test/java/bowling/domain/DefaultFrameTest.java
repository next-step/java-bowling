package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DefaultFrameTest {

    @Test
    void create() {
        Frame actual = new DefaultFrame(1, 2);

        assertThat(actual).isEqualTo(new DefaultFrame(1, 2));
    }

    @DisplayName("첫 번째 점수와 두 번째 점수의 합이 10을 넘으면 IllegalArgumentException 예외를 throw 한다.")
    @Test
    void validate_sum() {
        assertThatThrownBy(() -> new DefaultFrame(5, 6)).isInstanceOf(IllegalArgumentException.class);
    }
}
