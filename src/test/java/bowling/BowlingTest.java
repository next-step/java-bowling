package bowling;

import bowling.domain.Bowling;
import bowling.domain.ScoreUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {

    Bowling bowling = new Bowling();

    @BeforeEach
    void setUp() {
        bowling.addPlayerScore(0, 10);

        bowling.addPlayerScore(1, 9);
        bowling.addPlayerScore(1, 1);

        bowling.addPlayerScore(2, 0);
    }

    @DisplayName("각 프레임이 스트라이크이면 X")
    @Test
    void strikeTest() {
        assertThat(ScoreUI.frameOf(bowling.getFrameResult(0))).isEqualTo("x");
    }

    @DisplayName("프레임의 합이 10 이면 X")
    @Test
    void spareTest() {
        assertThat(ScoreUI.frameOf(bowling.getFrameResult(1))).isEqualTo("/");
    }

    @DisplayName("각 프레임이 미스이면 4|5")
    @Test
    void missTest() {
        bowling.addPlayerScore(2, 4);
        assertThat(ScoreUI.frameOf(bowling.getFrameResult(2))).isEqualTo("4");
        bowling.addPlayerScore(2, 5);
        assertThat(ScoreUI.frameOf(bowling.getFrameResult(2))).isEqualTo("5");
    }

    @DisplayName("핀을 하나도 쓰러트리지 못한 상태. -")
    @Test
    void gutterTest() {
        assertThat(ScoreUI.frameOf(bowling.getFrameResult(2))).isEqualTo("-");
    }


}
