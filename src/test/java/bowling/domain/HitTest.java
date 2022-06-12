package bowling.domain;

import bowling.exception.InvalidBoundHitException;
import bowling.exception.OverflowHitException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest(name = "Number:{0} 는 최대값이 아니다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void notMaximum(int number) {
        assertThat(Hit.valueOf(number).isMaximum()).isFalse();
    }

    @Test
    @DisplayName("10일 경우 최대값이다.")
    void maximum() {
        assertThat(Hit.valueOf(10).isMaximum()).isTrue();
    }

    @Test
    @DisplayName("두 Hit 의 총합이 10을 넘으면 예외를 반환한다.")
    void invalidSum() {
        Hit hit = Hit.valueOf(5);
        Hit anotherHit = Hit.valueOf(9);

        assertThatThrownBy(() -> hit.sum(anotherHit))
                .isInstanceOf(OverflowHitException.class)
                .hasMessage("10 개를 넘길 수 없습니다. 입력한 Hit 수: 5, 9");
    }

    @Test
    @DisplayName("두 Hit 의 총합으로 반환한다.")
    void sum() {
        Hit hit = Hit.valueOf(5);
        Hit anotherHit = Hit.valueOf(2);

        assertThat(hit.sum(anotherHit)).isEqualTo(Hit.valueOf(7));
    }

    @ParameterizedTest(name = "Number:{0} - Description:{1}")
    @CsvSource({
            "0,-",
            "1,1",
            "2,2",
            "3,3",
            "4,4",
            "5,5",
            "6,6",
            "7,7",
            "8,8",
            "9,9"
    })
    void description(int number, String expected) {
        assertThat(Hit.valueOf(number).description()).isEqualTo(expected);
    }
}