package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    private final int FIRST_FRAME = 2;
    private final int SECOND_FRAME = 3;
    private final int THIRD_FRAME = 5;
    private final int FINAL_FRAME = 18;
    private final int[] scores = {7, 3,
            10,
            5, 3,
            4, 6,
            7, 1,
            5, 5,
            10,
            10,
            9, 1,
            5, 5, 10};

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void 볼링게임_한프레임_진행() {
        IntStream.range(0, FIRST_FRAME)
                .forEach(count -> bowlingGame.playBowling(scores[count]));

        assertThat(bowlingGame.sumScore()).isEqualTo(sumExpectScore(FIRST_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(2);
    }

    @Test
    void 볼링게임_두프레임_진행() {
        IntStream.range(0, SECOND_FRAME)
                .forEach(count -> bowlingGame.playBowling(scores[count]));

        assertThat(bowlingGame.sumScore()).isEqualTo(sumExpectScore(SECOND_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(3);
    }

    @Test
    void 볼링게임_세프레임_진행() {
        IntStream.range(0, THIRD_FRAME)
                .forEach(count -> bowlingGame.playBowling(scores[count]));
        assertThat(bowlingGame.sumScore()).isEqualTo(sumExpectScore(THIRD_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(4);
    }

    @Test
    void 볼링게임_열프레임_진행() {
        IntStream.range(0, FINAL_FRAME)
                .forEach(count -> bowlingGame.playBowling(scores[count]));
        assertThat(bowlingGame.sumScore()).isEqualTo(sumExpectScore(FINAL_FRAME));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(10);
    }

    int sumExpectScore(int count) {
        return Arrays.stream(scores)
                .limit(count)
                .sum();
    }

    @Test
    void 다음_프레임_번호() {
        IntStream.range(0, THIRD_FRAME)
                .forEach(count -> bowlingGame.playBowling(scores[count]));
        assertThat(bowlingGame.getNextFrameNumber()).isEqualTo(4);

    }
}
