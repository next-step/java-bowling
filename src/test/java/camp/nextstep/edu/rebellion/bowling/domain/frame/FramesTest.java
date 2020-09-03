package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.game.Round;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {
    @DisplayName("프레임이 잘 생성 되는지 확인")
    @Test
    public void framesTest() {
        // given
        Frames frames = Frames.clear();

        // when & then
        assertAll(
                () -> assertThat(frames.getFrames()).hasSize(10),
                () -> assertThat(frames.isFinalFrameStrike()).isFalse()
        );
    }

    @DisplayName("프레임의 최종 라운드가 Strike 일 경우 올바르게 값이 반환 되는지 확인")
    @Test
    public void isFinalFrameStrikeTest() {
        // given
        Frames frames = Frames.clear();
        Round round = getRound(10);
        Frame frame = frames.findByRound(round);
        frame.markScore(10);

        // when & then
        assertThat(frames.isFinalFrameStrike()).isTrue();
    }

    @DisplayName("보너스 프레임은 1개 여야 만 함, 추가 생성 할 경우 예외 발생")
    @Test
    public void makeBonusFrameTest() {
        // given
        Frames frames = Frames.clear();

        // when
        frames.makeBonusFrame();

        // then
        assertAll(
                () -> assertThat(frames.getFrames()).hasSize(11),
                () -> assertThat(frames.isFinalFrameStrike()).isFalse(),
                () -> assertThat(frames
                        .findByRound(getRound(11)) instanceof BonusFrame
                ).isTrue()
        );

        // and & then
        assertThatThrownBy(() -> frames.makeBonusFrame())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 프레임은 하나만 생성할 수 있습니다");
    }

    private Round getRound(int wantedRound) {
        Round round = Round.reset();
        for (int i = 1; i < wantedRound; i++) {
            round.next();
        }
        return round;
    }
}
