package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StrikeTest {

    @Test
    void 종료_여부_판단() {
        assertThat(new Strike().isFinished()).isTrue();
    }

    @Test
    void 게임진행_예외발생() {
        assertThatThrownBy(() -> new Strike().bowl(new Pin(0)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 메시지_출력() {
        assertThat(new Strike().toString()).isEqualTo(Strike.STRIKE_MESSAGE);
    }

    @Test
    void Score_생성() {
        assertThat(new Strike().score()).isEqualTo(Score.ofStrike());
    }

    @Test
    void Score_계산() {
        assertThat(new Strike().calculateScore(new Score(10, 1)))
                .isEqualTo(new Score(20, 0));
    }
}
