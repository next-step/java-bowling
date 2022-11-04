package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("스트라이크 테스트")
public class StrikeTest {

    @DisplayName("공을 굴리는 경우 예외가 발생한다.")
    @Test
    void bowlException() {
        Strike strike = new Strike();

        assertThatThrownBy(() -> strike.bowl(Pin.of(1)));
    }
}
