package bowling.view;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ResultViewTest {

    @Test
    @DisplayName("화면 출력 - 빈 프레임 보드 출력")
    void head() {
        Player KYH = new Player("KYH");
        assertThat(ResultView.printName("KYH")).isEqualTo(String.format("|  %s |", "KYH"));
    }

    @ParameterizedTest
    @MethodSource("provideEmptyFrame")
    @DisplayName("빈 프레임 출력")
    void printEmptyScore(int count, String space) {
        assertThat(ResultView.printEmptyScore(count)).isEqualTo(space);
    }

    private static Stream<Arguments> provideEmptyFrame() {
        return Stream.of(
                Arguments.of(1, "       |"),
                Arguments.of(2, "       |       |"),
                Arguments.of(3, "       |       |       |")
        );
    }

    @ParameterizedTest
    @MethodSource("provideFirstScore")
    @DisplayName("1번째 투구시, 스코어 출력")
    void first(int first, String mark) {
        Frame frame = new NormalFrame();
        frame.pitching(first);
        assertThat(ResultView.convertScore(frame)).isEqualTo(mark);
    }

    private static Stream<Arguments> provideFirstScore() {
        return Stream.of(
                Arguments.of(0, "-"),
                Arguments.of(9, "9"),
                Arguments.of(10, "X")
        );
    }

    @ParameterizedTest
    @MethodSource("provideFirstSecondScore")
    @DisplayName("2번째 투구시, 스코어 출력")
    void second(int first, int second, String mark) {
        Frame frame = new NormalFrame();
        frame.pitching(first);
        frame.pitching(second);
        assertThat(ResultView.convertScore(frame)).isEqualTo(mark);
    }

    private static Stream<Arguments> provideFirstSecondScore() {
        return Stream.of(
                Arguments.of(0, 0, "-"),
                Arguments.of(8, 1, "8|1"),
                Arguments.of(8, 2, "8|/"),
                Arguments.of(0, 2, "-|2"),
                Arguments.of(7, 0, "7|-")
        );
    }

    @ParameterizedTest
    @MethodSource("provideBonusScore")
    @DisplayName("3번째 투구시, 스코어 출력")
    void bonus(int first, int second, int bonus, String mark) {
        Frame frame = new FinalFrame();
        frame.pitching(first);
        frame.pitching(second);
        frame.pitching(bonus);
        assertThat(ResultView.convertScore(frame)).isEqualTo(mark);
    }

    private static Stream<Arguments> provideBonusScore() {
        return Stream.of(
                Arguments.of(1, 9, 1, "1|/|1"),
                Arguments.of(1, 9, 0, "1|/|-"),
                Arguments.of(1, 9, 10, "1|/|X"),
                Arguments.of(0, 10, 10, "-|/|X"),
                Arguments.of(10, 2, 8, "X|2|/"),
                Arguments.of(10, 0, 10, "X|-|/")
        );
    }

    @Test
    @DisplayName("화면 출력 - 스코어 프레임 보드 출력")
    void test() {
        Player KYH = new Player("KYH");
        Frames frames = new Frames();
        frames.addHittingPinsAtCurrentFrame(3);
        frames.addHittingPinsAtCurrentFrame(7);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(3);
        frames.addHittingPinsAtCurrentFrame(4);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(6);
        frames.addHittingPinsAtCurrentFrame(4);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(0);
        frames.addHittingPinsAtCurrentFrame(0);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(7);
        frames.addHittingPinsAtCurrentFrame(0);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(0);
        frames.addHittingPinsAtCurrentFrame(7);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(0);
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(10);
        frames.moveNextFrame();
        frames.addHittingPinsAtCurrentFrame(10);
        frames.addHittingPinsAtCurrentFrame(1);
        frames.addHittingPinsAtCurrentFrame(9);
        ResultView.printScoreFrame(KYH, frames);
    }


}