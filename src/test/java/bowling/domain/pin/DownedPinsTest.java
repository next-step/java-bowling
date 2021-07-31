package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("볼링 핀에 대한 클래스 테스트")
class DownedPinsTest {

    @DisplayName("쓰러진 볼링핀을 표현하는 Pin 클래스는 쓰러진 핀의 수를 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(DownedPins.from(0)).isInstanceOf(DownedPins.class);
    }

    @DisplayName("쓰러진 핀의 값이 0 보다 작거나 10 보다 크면 예외를 발생시킨다")
    @ValueSource(ints = {-1, 11})
    @ParameterizedTest
    void initException(int numOfDownedPins) {
        assertThatThrownBy(() -> DownedPins.from(numOfDownedPins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쓰러진 핀의 갯수가 같다면 동일하다고 판단한다")
    @MethodSource
    @ParameterizedTest
    void equals(int numOfDownedPins, DownedPins expectedDownedPins) {
        assertThat(DownedPins.from(numOfDownedPins)).isEqualTo(expectedDownedPins);
    }

    private static Stream<Arguments> equals() {
        return Stream.of(
                Arguments.of(5, DownedPins.from(5)),
                Arguments.of(10, DownedPins.from(10))
        );
    }

    @DisplayName("핀이 모두 쓰러졌는지 판별해서 반환한다")
    @MethodSource
    @ParameterizedTest
    void isAllDown(DownedPins downedPins, boolean expectedValue) {
        assertThat(downedPins.isAllDown()).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> isAllDown() {
        return Stream.of(
                Arguments.of(DownedPins.from(5), false),
                Arguments.of(DownedPins.from(10), true)
        );
    }

}
