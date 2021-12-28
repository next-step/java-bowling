package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoundTest {


    @Test
    @DisplayName("라운드의 Round는 값은 0-9 이다.")
    void createValidTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> Round.of(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> Round.of(10));
    }

    @Test
    @DisplayName("라운드 0 이, 시작 라운드이다.")
    void isStartRoundTest() {
        assertThat(Round.of(0).isStartRound()).isTrue();
        assertThat(Round.of(1).isStartRound()).isFalse();
    }

    @Test
    @DisplayName("1회 이전 Round가 반환되며, 최초 라운드 이전 라운드는 예외가 발생한다.")
    void beforeTest() {
        assertThat(Round.of(1).before()).isEqualTo(Round.of(0));
        assertThatIllegalArgumentException().isThrownBy(() -> Round.of(0).before());
    }
}