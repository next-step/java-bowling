package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.state.StateExpression;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {

    @DisplayName("Miss 상태가 아님을 반환")
    @Test
    public void isMiss() {
        assertThat(Strike.of().isMiss())
                .isFalse();
    }

    @DisplayName("Strike 점수에 대한 문자열을 반환")
    @Test
    public void getDesc() {
        assertThat(Strike.of().getDesc())
                .isEqualTo(StateExpression.STRIKE);
    }

    @DisplayName("해당 상태에서 공을 굴리면 예외를 반환")
    @Test
    public void bowl() {
        assertThatThrownBy(() -> Strike.of().bowl(PinCount.of(PinCount.MAX_COUNT)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("종료 조건을 만족")
    @Test
    public void isFinish() {
        assertThat(Strike.of().isFinish())
                .isTrue();
    }
}
