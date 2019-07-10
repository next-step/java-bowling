package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    private final int FIRST = 1;
    private final int SECOND = 2;
    private final int THIRD = 3;
    private final int FINAL = 10;
    private final int FIRST_FRAME = 2;
    private final int SECOND_FRAME = 3;
    private final int THIRD_FRAME = 5;
    private final int FINAL_FRAME = 18;
    private final int[] SCORES = {7, 3,
            10,
            5, 3,
            4, 6,
            7, 1,
            5, 5,
            10,
            10,
            9, 1,
            5, 5, 10};
    private final String POINT_RESULT = "7|/  | X    | 5|3  | 4|/  | 7|1  | 5|/  | X    | X    | 9|/  | 5|/|X|";
    private final String SCORE_RESULT = "20   | 38   | 46   | 63   | 71   | 91   | 121  | 141  | 156  | 176  | ";

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void 볼링게임_한프레임_진행() {
        IntStream.range(0, FIRST_FRAME)
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));

        assertThat(sumRealScore(bowlingGame, FIRST)).isEqualTo(sumExpectScore(FIRST_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(2);
    }

    @Test
    void 볼링게임_두프레임_진행() {
        IntStream.range(0, SECOND_FRAME)
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));

        assertThat(sumRealScore(bowlingGame, SECOND)).isEqualTo(sumExpectScore(SECOND_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(3);
    }

    @Test
    void 볼링게임_세프레임_진행() {
        IntStream.range(0, THIRD_FRAME)
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));
        assertThat(sumRealScore(bowlingGame, THIRD)).isEqualTo(sumExpectScore(THIRD_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(4);
    }

    @Test
    void 볼링게임_열프레임_진행() {
        IntStream.range(0, FINAL_FRAME)
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));
        assertThat(sumRealScore(bowlingGame, FINAL)).isEqualTo(sumExpectScore(FINAL_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(10);
    }

    @DisplayName("열 번째 프레임까지 모두 친 상태는 isGameOver==True")
    @Test
    void isGameOverTest() {
        IntStream.range(0, FINAL_FRAME)
                .peek(count -> assertThat(bowlingGame.isGameOver()).isFalse())
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));
        assertThat(bowlingGame.isGameOver()).isTrue();
    }

    @DisplayName("세 프레임 친 이후, getNextFrameNumber는 다음 프레임 4번을 가리킴")
    @Test
    void getNextFrameNumberTest() {
        IntStream.range(0, THIRD_FRAME)
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(4);
    }

    @DisplayName("세 프레임 친 이후, lastPosition은 직전의 두 번째 프레임 2번을 가리킴")
    @Test
    void lastPositionTest() {
        IntStream.range(0, THIRD_FRAME)
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));
        assertThat(bowlingGame.lastPosition()).isEqualTo(SECOND);
    }

    @DisplayName("열 프레임 모두 친 후, 볼링 결과 출력")
    @Test
    void getFormattedPointResultTest() {
        IntStream.range(0, FINAL_FRAME)
                .peek(count -> assertThat(bowlingGame.isGameOver()).isFalse())
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));
        assertThat(bowlingGame.getFormattedPointResult()).isEqualTo(POINT_RESULT);
    }

    @DisplayName("열 프레임 모두 친 후, 점수 결과 출력")
    @Test
    void getFormattedScoreResultTest() {
        IntStream.range(0, FINAL_FRAME)
                .peek(count -> assertThat(bowlingGame.isGameOver()).isFalse())
                .forEach(count -> bowlingGame.playBowling(SCORES[count]));
        assertThat(bowlingGame.getFormattedScoreResult()).isEqualTo(SCORE_RESULT);
    }

    int sumRealScore(BowlingGame bowlingGame, int position) {
        int normalScore = IntStream.range(0, position == FINAL ? position - 1 : position)
                .boxed()
                .mapToInt(count -> bowlingGame.getNormalFrame(count).sumScore())
                .sum();
        int finalScore = bowlingGame.getFinalFrame().sumScore();
        return normalScore + (finalScore == -1 ? 0 : finalScore);
    }

    int sumExpectScore(int count) {
        return Arrays.stream(SCORES)
                .limit(count)
                .sum();
    }
}
