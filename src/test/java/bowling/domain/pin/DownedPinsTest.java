package bowling.domain.pin;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static bowling.domain.Fixture.*;
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
                Arguments.of(5, DOWNED_PINS_5),
                Arguments.of(10, DOWNED_PINS_10)
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
                Arguments.of(DOWNED_PINS_5, false),
                Arguments.of(DOWNED_PINS_10, true)
        );
    }

    @DisplayName("쓰러진 핀 두 객체를 더해 새로운 쓰러진 핀 객체를 반환한다")
    @MethodSource
    @ParameterizedTest
    void add(DownedPins downedPins, DownedPins anotherDownedPins, DownedPins expectedDownedPins) {
        assertThat(downedPins.add(anotherDownedPins)).isEqualTo(expectedDownedPins);
    }

    private static Stream<Arguments> add() {
        return Stream.of(
                Arguments.of(DOWNED_PINS_5, DOWNED_PINS_5, DOWNED_PINS_10),
                Arguments.of(DOWNED_PINS_5, DOWNED_PINS_2, DOWNED_PINS_7)
        );
    }

    @DisplayName("쓰러진 핀 두개의 값이 한계치를 지나면 예외를 발생시킨다")
    @MethodSource
    @ParameterizedTest
    void addException(DownedPins downedPins, DownedPins anotherDownedPins) {
        assertThatThrownBy(() -> downedPins.add(anotherDownedPins)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> addException() {
        return Stream.of(
                Arguments.of(DOWNED_PINS_5, DOWNED_PINS_10),
                Arguments.of(DOWNED_PINS_5, DOWNED_PINS_7)
        );
    }

    @DisplayName("쓰러진 핀으로 스코어를 만들어 낼 수 있다")
    @Test
    void score() {
        assertThat(DOWNED_PINS_5.score()).isEqualTo(Score.from(5));
    }

}
