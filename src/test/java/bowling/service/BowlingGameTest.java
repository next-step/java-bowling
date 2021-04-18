package bowling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("볼링 게임 초구시 시도 횟수 확인 테스트")
    @Test
    void bowl_시도횟수_확인테스트() {
        // given
        BowlingGame bowlingGame = new BowlingGame("KSR");
        // when
        bowlingGame.bowl(3);
        int tryingCount = bowlingGame.tryingCount();
        // then
        assertThat(tryingCount).isEqualTo(1);
    }

    @DisplayName("볼링 게임 2구 시 다음 프레임으로 넘어가기 테스트")
    @Test
    void bowl_프레임_증가_확인테스트() {
        BowlingGame bowlingGame = new BowlingGame("KSR");
        bowlingGame.bowl(5);
        bowlingGame.bowl(5);

        int frameNumber = bowlingGame.frameNumber();

        assertThat(frameNumber).isEqualTo(2);
    }
}
