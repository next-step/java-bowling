package bowling.service;

import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("볼링 게임 초구시 시도 횟수 확인 테스트")
    @Test
    void bowl_시도횟수_확인테스트() {
        // given
        BowlingGame bowlingGame = new BowlingGame("KSR");
        // when
        bowlingGame.bowl(3);
        List<Frame> frames = bowlingGame.frames();
        // then
        assertThat(frames.get(0).tryCount()).isEqualTo(1);
    }

    @DisplayName("볼링 게임 2구 시 다음 프레임으로 넘어가기 테스트")
    @Test
    void bowl_프레임_증가_확인테스트() {
        BowlingGame bowlingGame = new BowlingGame("KSR");
        bowlingGame.bowl(5);
        bowlingGame.bowl(5);

        int frameNumber = bowlingGame.frames.frameNumber();

        assertThat(frameNumber).isEqualTo(1);
    }

    @DisplayName("볼링 게임 1프레임 투구 후에 2 프레임으로 넘어가는지 확인 테스트")
    @Test
    void bowl_다음_프레임_추가테스트() {
        BowlingGame bowlingGame = new BowlingGame("KSR");
        bowlingGame.bowl(5);
        bowlingGame.bowl(5);
        bowlingGame.bowl(5);
        bowlingGame.bowl(5);

        int frameNumber = bowlingGame.frames.frameNumber();

        assertThat(frameNumber).isEqualTo(2);
    }
}
