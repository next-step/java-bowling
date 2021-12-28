package bowling.domain.state;

import bowling.domain.PinsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StrikeTest {
    private ThrowingState strike;

    @BeforeEach
    void beforeEach() {
        strike = Strike.create();
    }

    @DisplayName("한 프레임 첫 투구에 스트라이크인 경우, 재 투구 예외 확인")
    @Test
    void bowl() {
        // when & then
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> strike.bowl(PinsTest.FIVE));
    }

    @DisplayName("한 프레임의 스트라이크 이후 종료 상태 확인")
    @Test
    void isEnd() {
        // when & then
        assertThat(strike.isEnd()).isTrue();
    }

    @DisplayName("한 프레임에서 스트라이크 상태 symbol 확인")
    @Test
    void symbol() {
        // when & then
        assertThat(strike.symbol()).isEqualTo("X");
    }
}
