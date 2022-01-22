package bowling.state.ended;

import bowling.Pins;
import bowling.state.Throwing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndedTest {

    @DisplayName("끝났으므로 굴릴 수 없음.")
    @Test
    void 예외_굴릴_수_없음() {
        Throwing strike = new Strike();
        assertThatThrownBy(() -> strike.bowl(new Pins(4)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("종료된 프레임 입니다.");
    }
}