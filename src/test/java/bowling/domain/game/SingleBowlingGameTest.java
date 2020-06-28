package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class SingleBowlingGameTest {

    @DisplayName("객체 정상 생성 - 파라미터로 Player String 이름과 Frames를 주입받는다.")
    @Test
    public void makeSingleBowlingGame() {
        assertThatCode(() -> {
            SingleBowlingGame.of("PJH", Frames.initiate());
        }).doesNotThrowAnyException();
    }

    @DisplayName("현재 플레이중인 프레임이 완료되었으면 True 반환")
    @Test
    public void isCurrentFrameFinished_True() {
        SingleBowlingGame singleBowlingGame = SingleBowlingGame.of("TDD", Frames.initiate());

        singleBowlingGame.bowl(PitchScore.valueOf(10));

        assertThat(singleBowlingGame.isCurrentFrameFinished()).isTrue();
    }

    @DisplayName("현재 플레이중인 프레임이 완료되지 않으면 False를 반환")
    @Test
    public void isCurrentFrameFinished_False() {
        SingleBowlingGame singleBowlingGame = SingleBowlingGame.of("TDD", Frames.initiate());

        singleBowlingGame.bowl(PitchScore.valueOf(3));

        assertThat(singleBowlingGame.isCurrentFrameFinished()).isFalse();
    }
}
