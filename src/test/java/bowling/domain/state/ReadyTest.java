package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadyTest {
    private State ready;

    @BeforeEach
    void setUp() {
        ready = new Ready();
    }

    @Test
    @DisplayName("Ready 상태에서는 공을 굴릴 수 있다.")
    void isFinish() {
        assertThat(ready.isFinish()).isFalse();
    }

    @Test
    @DisplayName("쓰러뜨린 핀 수는 0 이상 10 이하여야 한다.")
    void init() {
        assertThat(ready.bowl(10)).isNotNull();
        assertThat(ready.bowl(0)).isNotNull();

        assertThrows(IllegalArgumentException.class, () -> ready.bowl(-1));
        assertThrows(IllegalArgumentException.class, () -> ready.bowl(11));
    }

    @Test
    @DisplayName("Ready 상태에서 공을 굴려 핀을 10개 쓰러뜨리면 Strike 를 반환한다.")
    void bowlStrike() {
        State strike = ready.bowl(10);
        assertThat(strike).isInstanceOf(Strike.class);
        assertThat(strike).isNotInstanceOf(Trying.class);
    }

    @ParameterizedTest
    @DisplayName("Ready 상태에서 공을 굴려 핀을 10개가 아니면 Trying 를 반환한다.")
    @ValueSource(ints = {0, 5, 9})
    void bowlTrying(int fallenPinCount) {
        State strike = ready.bowl(fallenPinCount);
        assertThat(strike).isInstanceOf(Trying.class);
        assertThat(strike).isNotInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("출력 시 공백 값을 가진다.")
    void print() {
        assertThat(ready.print()).isEqualTo("");
    }
}
