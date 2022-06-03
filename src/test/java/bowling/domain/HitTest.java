package bowling.domain;

import bowling.exception.InvalidBoundHitException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HitTest {

    @Test
    @DisplayName("히트는 0 ~ 10 사이만 가능하다.")
    void invalidHit() {
        assertThatThrownBy(() -> Hit.valueOf(11))
                .isInstanceOf(InvalidBoundHitException.class)
                .hasMessage("Hit 는 0 ~ 10 사이의 값만 가능합니다.");
    }

    @ParameterizedTest(name = "캐싱된 Hit 테스트 - Number:{0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void cache(int number) {
        assertThat(Hit.valueOf(number) == Hit.valueOf(number)).isTrue();
    }

}