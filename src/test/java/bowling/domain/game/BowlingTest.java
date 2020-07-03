package bowling.domain.game;

import bowling.domain.player.Player;
import bowling.exception.game.CanNotAccessMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingTest {
    @ParameterizedTest
    @MethodSource("normalBowling")
    @DisplayName("일반 볼링")
    void bowl(List<Integer> countOfPins) {
        Bowling bowling = new Bowling(Player.of("abc"));

        for (Integer countOfPin : countOfPins) {
            bowling.bowl(countOfPin);
        }

        System.out.println(bowling.toString());
    }

    static Stream<Arguments> normalBowling() {
        return Stream.of(Arguments.of(Arrays.asList(10, 1, 2, 0, 10)));
    }

    @Test
    @DisplayName("faxnumber 유효한지 확인")
    void bowl_frameNumber() {
        Bowling bowling = new Bowling(Player.of("abc"));

        bowling.bowl(10);
        assertThat(bowling.getFrameNumber()).isEqualTo(FrameNumber.of(2));
    }

    @ParameterizedTest
    @MethodSource("bowling")
    @DisplayName("볼링 보드 출력해보기")
    void bowling(List<Integer> countOfPins) {
        Bowling bowling = new Bowling(Player.of("abc"));

        for (Integer countOfPin : countOfPins) {
            bowling.bowl(countOfPin);
        }

        System.out.println(bowling.toString());
    }

    static Stream<Arguments> bowling() {
        return Stream.of(
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 10, 1, 2)),
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 1, 9, 1)),
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 1, 9, 10)));
    }

    @ParameterizedTest
    @MethodSource("finalBowling")
    @DisplayName("볼링 입력 오류")
    void bowl_exception(List<Integer> countOfPins) {
        Bowling bowling = new Bowling(Player.of("abc"));

        assertThatThrownBy(() -> {
            for (Integer countOfPin : countOfPins) {
                bowling.bowl(countOfPin);
            }
        }).isInstanceOf(CanNotAccessMethod.class);

        System.out.println(bowling.toString());
    }

    static Stream<Arguments> finalBowling() {
        return Stream.of(
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 10, 1, 2, 3)),
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 10, 10, 2, 3, 4)));
    }

    @ParameterizedTest
    @MethodSource("isEnd")
    @DisplayName("볼링 게임 끝나지 않은 경우 false, 끝난 경우 true를 반환한다.")
    void isEnd(List<Integer> countOfPins, boolean expected) {
        Bowling bowling = new Bowling(Player.of("abc"));

        for (Integer countOfPin : countOfPins) {
            bowling.bowl(countOfPin);
        }

        assertThat(bowling.isEnd()).isEqualTo(expected);
    }

    static Stream<Arguments> isEnd() {
        return Stream.of(
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 10), false),
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 10, 1), false),
                arguments(Arrays.asList(10, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 1, 2, 3, 4, 10, 10, 1, 2), true));
    }
}
