package bowling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("볼링 게임 초구시 시도 횟수 확인 테스트")
    @Test
    void bowl_볼링_게임_투구테스트() {
        // given
        BowlingGame bowlingGame = new BowlingGame("KSR");
        // when
        bowlingGame.bowl(3);
        int tryingCount = bowlingGame.tryingCount();
        // then
        assertThat(tryingCount).isEqualTo(1);
    }
}
