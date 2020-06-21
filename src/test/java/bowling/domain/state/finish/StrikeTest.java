package bowling.domain.state.finish;

import bowling.domain.state.StateExpression;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

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
}
