package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Miss 테스트")
class MissTest {

    @DisplayName("Miss 에서 공을 굴리는 경우 예외가 발생한다.")
    @Test
    void bowlException() {

        final Miss miss = new Miss(new Pin(0), new Pin(9));

        assertThatThrownBy(() -> miss.bowl(new Pin(1)));
    }
}
