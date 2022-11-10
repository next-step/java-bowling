package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("미스 테스트")
public class MissTest {
    @DisplayName("공을 굴리는 경우 예외가 발생한다.")
    @Test
    void bowlException() {
        Miss miss = new Miss(Pin.of(0), Pin.of(9));

        assertThatThrownBy(() -> miss.bowl(Pin.of(1)));
    }
}
