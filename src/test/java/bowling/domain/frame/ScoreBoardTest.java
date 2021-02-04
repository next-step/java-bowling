package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.internal.util.collections.ArrayHelper.toList;

public class ScoreBoardTest {

    @Test
    @DisplayName("최초의 프레임은 1")
    void printCurrentFrame() {
        ScoreBoard board = new ScoreBoard();

        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크 이후 2프레임으로 이동")
    void secondFrameAfterStrike() {
        ScoreBoard board = new ScoreBoard();

        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(10);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("MISS 이후 2프레임으로 이동")
    void secondFrameAfterMiss() {
        ScoreBoard board = new ScoreBoard();

        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(5);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(1);
        board.record(5);
        assertThat(board.getCurrentFrameNumber()).isEqualTo(2);
    }

    private static Stream<Arguments> gameScenarios() {
        return Stream.of(
                Arguments.of(new int[]{
                        10,
                        9, 1,
                        8, 2,
                        10,
                        10,
                        4, 5,
                        10,
                        0, 0,
                        10,
                        10, 10, 10
                }, new String[]{
                        "X",
                        "9 | /",
                        "8 | /",
                        "X",
                        "X",
                        "4 | 5",
                        "X",
                        "- | -",
                        "X",
                        "X | X | X"}),
                Arguments.of(new int[]{
                        5, 5,
                        0, 10,
                        10,
                        3, 4,
                        4, 5,
                        10,
                        10,
                        10,
                        8, 1,
                        6, 3
                }, new String[]{
                        "5 | /",
                        "- | /",
                        "X",
                        "3 | 4",
                        "4 | 5",
                        "X",
                        "X",
                        "X",
                        "8 | 1",
                        "6 | 3"})
        );
    }

    @MethodSource("gameScenarios")
    @ParameterizedTest
    @DisplayName("게임 종료조건 테스트")
    void isEnd(int[] scenario) {
        ScoreBoard board = new ScoreBoard();

        for (int downedPin : scenario) {
            assertThat(board.isEnd()).isFalse();
            board.record(downedPin);
        }

        assertThat(board.isEnd()).isTrue();
    }

    @Test
    @DisplayName("출력 확인. 최초 조건")
    void initialBoardDescription() {
        ScoreBoard board = new ScoreBoard();
        List<String> expected = new ArrayList<>();
        expected.add("");

        assertThat(board.getDescriptions()).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 프레임의 Strike 출력")
    void boardDescriptionWithFirstStrike() {
        ScoreBoard board = new ScoreBoard();
        board.record(10);

        List<String> expected = new ArrayList<>();
        expected.add("X");

        assertThat(board.getDescriptions()).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 프레임의 Miss 출력")
    void boardDescriptionWithFirstMiss() {
        ScoreBoard board = new ScoreBoard();
        board.record(4);
        board.record(4);

        List<String> expected = new ArrayList<>();
        expected.add("4 | 4");

        assertThat(board.getDescriptions()).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 프레임의 Spare 출력")
    void boardDescriptionWithFirstSpare() {
        ScoreBoard board = new ScoreBoard();
        board.record(4);
        board.record(6);

        List<String> expected = new ArrayList<>();
        expected.add("4 | /");

        assertThat(board.getDescriptions()).isEqualTo(expected);
    }

    @MethodSource("gameScenarios")
    @ParameterizedTest
    @DisplayName("게임 전체 출력 확인")
    void wholeDescription(int[] scenario, String[] expected) {
        ScoreBoard board = new ScoreBoard();

        for (int downedPin : scenario) {
            board.record(downedPin);
        }

        assertThat(board.getDescriptions()).isEqualTo(toList(expected));
    }

    @Test
    @DisplayName("기초적인 Score List 생성 방법")
    void scores() {
        ScoreBoard board = new ScoreBoard();

        List<Integer> scores = board.getAccumulatedScoresOfFrames();
    }
}
