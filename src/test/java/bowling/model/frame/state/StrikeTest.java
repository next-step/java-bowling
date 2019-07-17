package bowling.model.frame.state;

import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.DownPin.DOWN_ALL;
import static bowling.model.DownPin.MAX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StrikeTest {

    @DisplayName("싱글톤 확인")
    @Test
    void check_singleton() {
        assertThat(Strike.getInstance()).isEqualTo(Strike.getInstance());
    }

    @DisplayName("핀의 개수가 " + MAX + "인 경우 스트라이크이다")
    @Test
    void isMatch_pinsMax_thanTrue() {
        // when
        boolean result = Strike.isMatch(DOWN_ALL);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("볼을 던지는데 실패한다")
    @Test
    void bowl_exception() {
        // exception
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> Strike.getInstance().bowl(DOWN_ALL));
    }

    @DisplayName("스트라이크 일 시 종료한다")
    @Test
    void isFinished_true() {
        // given
        State strike = Strike.getInstance();

        // when & than
        assertThat(strike.isFinished()).isTrue();
    }
}