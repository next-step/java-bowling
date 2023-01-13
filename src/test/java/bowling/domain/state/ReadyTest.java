package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    @Test
    void 종료_여부_판단() {
        assertThat(new Ready().isFinished()).isFalse();
    }

    @Test
    void 게임진행_Strike() {
        assertThat(new Ready().bowl(new Pin(10)))
                .isInstanceOf(Strike.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    void 게임진행_FirstPin(int amount) {
        assertThat(new Ready().bowl(new Pin(amount)))
                .isInstanceOf(FirstPin.class);
    }

    @Test
    void 메시지_출력() {
        assertThat(new Ready().toString()).isEqualTo(Ready.READY_MESSAGE);
    }

    @Test
    void Score_생성() {
        assertThatThrownBy(() -> new Ready().score())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void Score_계산() {
        assertThatThrownBy(() -> new Ready().calculateScore(Score.ofStrike()))
                .isInstanceOf(IllegalStateException.class);
    }
}