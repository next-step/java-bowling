package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class HitTest {

    @Test
    @DisplayName("최댓값 테스트")
    void max() {
        Hit hit = new Hit(Hit.MAX);
        assertThat(hit.isMax()).isTrue();
        assertThat(hit.isMin()).isFalse();
    }

    @Test
    @DisplayName("최솟값 테스트")
    void min() {
        Hit hit = new Hit(Hit.MIN);
        assertThat(hit.isMax()).isFalse();
        assertThat(hit.isMin()).isTrue();
    }

    @Test
    @DisplayName("범위 초과 테스트")
    void range() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Hit(11));
    }

    @Test
    @DisplayName("add에 의한 범위 초과 테스트")
    void range_add() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Hit(3).plus(new Hit(8)));
    }
}
