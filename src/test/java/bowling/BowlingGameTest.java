package bowling;

import bowling.bowler.Bowler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = BowlingGame.create(Bowler.of("LHG"));
    }

    @Test
    @DisplayName("볼링 시작")
    void bowlingStart() {
        assertThat(bowlingGame.getFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("다음 프레임으로 이동 확인")
    void playBowl() {
        bowlingGame.bowl("10");
        assertThat(bowlingGame.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("볼링 종료")
    void bowlingEnd() {
        for (int i = 0; i < 12; i++) {
            bowlingGame.bowl("10");
        }
        assertThat(bowlingGame.getFrames().isEnd()).isTrue();
    }

}
