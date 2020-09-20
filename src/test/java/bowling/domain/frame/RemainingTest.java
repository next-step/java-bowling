package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RemainingTest {

    @Test
    @DisplayName("STRIKE 상태는 2회의 보너스 점수가 주어지는 테스트")
    void strike() {
        assertThat(Remaining.STRIKE).isEqualTo(Remaining.of(2));
    }

    @Test
    @DisplayName("SPARE 상태는 1회의 추가 점수가 주어지는 테스트")
    void spare() {
        assertThat(Remaining.SPARE).isEqualTo(Remaining.of(1));
    }

    @Test
    @DisplayName("Remaining 감소 decrement() 메소드 테스트")
    void decrement() {
        assertThat(Remaining.of(2).decrement()).isEqualTo(Remaining.of(1));
    }

    @Test
    @DisplayName("Remaining 값이 0인 경우 isZero() 메소드 테스트")
    void isZero() {
        assertThat(Remaining.of(0).isZero()).isTrue();
    }
}
